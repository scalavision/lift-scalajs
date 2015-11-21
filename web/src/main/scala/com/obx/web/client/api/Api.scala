
package com.obx.web.client.api

import rx._
import scala.scalajs.js.annotation.JSExport
import com.obx.model.Model._
import scala.scalajs.js

@JSExport("com.obx.web.client.api.Api")
object Api {

  @JSExport
  def search(searchText:String):Unit = {
    ClientToServerBridge.search(searchText)
  }
    
  @JSExport
  def setSearchResult(result:Seq[SearchItem]):Unit = {
    println("Received a result from search .... " + result)
    searchResult() = result
  }
  
  val searchResult = Var(
    Seq():Seq[SearchItem]
  )
  
  val activeDate = Var(
    new js.Date()
  )
  
  val activeMeal = Var(
    Breakfast:Meal
  )
  
  val calendarContainer = "calendar-container"
  val datepickerDiary = "datepicker-diary"
  
}
