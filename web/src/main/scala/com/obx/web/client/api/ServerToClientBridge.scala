package com.obx.web.client.api

import com.obx.model.Model.SearchItem
import scala.scalajs.js.annotation.JSExport

@JSExport("com.obx.web.client.api.ServerToClientBridge")
object ServerToClientBridge {
  
  @JSExport
  def setSearchResult(result:String):Unit = {
    println("received something : " + result)
    Api.setSearchResult(upickle.default.read[Seq[SearchItem]](result))
  }
  
}
