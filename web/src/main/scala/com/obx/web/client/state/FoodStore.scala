
package com.obx.web.client.state

import rx._
import com.obx.model.Model._
import com.obx.web.client.logger._
import scala.scalajs.js

trait FoodStore {
  private var interval: js.UndefOr[js.timers.SetIntervalHandle] = js.undefined
  private val foodStore = Var(Seq.empty[FoodItem])
 
  
  def updateFoodStore(newFoodItems: Seq[FoodItem]):Unit = {
//    if(newFoodItems != foodItems()){
    if(newFoodItems != foodStore()){
      foodStore() = newFoodItems
    }
  }
  
  def foodItems: Rx[Seq[FoodItem]] = 
    foodStore
  
//  updateFoodStore(foodExample)
//  
//  def tick() = {
//    foodExample = foodExample.drop(1)
//    updateFoodStore(foodExample)
//    log.info("decrease the length of foodStore")
//  }
    
    
//  interval = js.timers.setInterval(4000)(tick())
  
}

object FoodStore extends FoodStore
