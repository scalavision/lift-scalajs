package code.snippet

import net.liftweb.common.Loggable
import net.liftweb.http.js._
import net.liftweb.http.js.JE._
import net.liftweb.http.js.JsCmds._
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Extraction
import net.liftweb.json.JsonAST._
import scala.xml.NodeSeq
import net.liftweb.http._
import net.liftmodules.extras.JsExtras
import net.liftmodules.extras.JsExtras._
import com.obx.model.Model._

class ServerApiSnippet extends Loggable {
  
  implicit val formats = DefaultFormats

  // javascript path to the javascript class in the client
  // the class exists in the web client project
  val clientApiForServer = "com.obx.web.client.api.ServerToClientBridge"
  
   val database = Seq(
    "Banana",
    "Apple",
    "Orange",
    "Strawberry"
  )
  
  def render(in:NodeSeq):NodeSeq = {
    for(sess <- S.session) yield {
      
      val clientProxy = sess.serverActorForClient("window.actorsBridge")
      val serverToClientForwarder = S.findOrCreateComet("ServerToClientForwarder")
      serverToClientForwarder.map(
        forwarder => S.addComet(forwarder)
      )
      
      // Using Lift DSL from Lift extras module,
      // we create an instance of our ServerClientBridge with JsExtras.CallNew
      // we set our window object connected bridge to point to the ServerClientBridge:
      // window.actorsBridge = com.obx.web.client.api.ServerToClientBridge
      // it makes it possible to call window.actorsBridge from server, and then
      // forward to functions implemented in ServerClientBridge class on the scalajs client
      val serverToClientBridge = SetExp(
        JsVar("window.actorsBridge"), JsExtras.CallNew(clientApiForServer)
      )
      
      def search(searchStringJS:JValue):Unit = {
        import net.liftweb.json.Serialization.{write}
        
        val searchText = searchStringJS.extract[String]
        val result:Seq[SearchItem] = 
          database.filter(s => s.contains(searchText)).map(
            s => SearchItem("1", s)
          )
        
        val resultJson: String = 
          write(Extraction.decompose(result))
        
        // Adding setSearchResult to window.actorsBridge, thus we can
        // implement this method in ServerToClientBridge class in web client
        // and call it from server via window.actorsBridge
        val cmd = s"window.actorsBridge.setSearchResult('${resultJson}');"
        
        clientProxy ! JE.JsRaw(cmd)
        
        serverToClientForwarder.map{
          forwarder => { 
            println("sending a message via comet ..")
            println(forwarder)
            forwarder ! "test" 
            
          }
        }
        
      }
      
      // Creating bridge so that the client can call functions
      // on the server. There is a Javascript facade implemented
      // in com.obx.web.client.api.ClientToServerBridge.scala in
      // client project
      val script = SetExp(JsVar("ClientToServerBridge"), 
        sess.buildRoundtrip(List[RoundTripInfo](
          "search" -> search _
          )
        )
      )
      
      S.appendGlobalJs(serverToClientBridge)
      S.appendGlobalJs(script)
      
    }
    
    in
  }
  
}
