
package code.comet

import net.liftweb.common.Loggable
import net.liftweb.http.CometActor
//import net.liftweb.http.CometListener
import net.liftweb.http.js.JE
import net.liftweb.http.js.JsCmd
import net.liftweb.util.ClearClearable

case class Action() extends JsCmd {
  override val toJsCmd = JE.JsRaw("console.log('hello wolrd');").toJsCmd
}

class ServerToClientForwarder extends CometActor with Loggable{

  def render = ClearClearable
  
  override def  lowPriority: PartialFunction[Any, Unit] = {
    case _ => {
      println("recieved update from snippet ..")
      logger.info("sending action to the server")  
      partialUpdate(Action())
    }
  }
  
}
