package com.obx.web.client

import org.scalajs.dom
import scalatags.JsDom.all._
import scala.scalajs.js.JSApp
import com.obx.web.client.api.Api
import com.obx.web.client.logger._
import scala.scalajs.js

object WebClient extends JSApp {
  
  def main(): Unit = {
    // Need a small timeout to assure
    // all classes has settled after page load
    dom.window.setTimeout( { () => 
        Api.search("Ban")
      }
      , 1200)
  
    println("Did website refresh by itself ???")
  }
  
}
