package com.obx.model

object Model {
  
  case class FoodItem(
    _id:String, 
    name:String, 
    kiloJoule:Double, 
    kiloCalories:Double, 
    fat:Double, 
    carbohydrates:Double, 
    protein:Double)
  
  
  case class SearchItem(
    _id: String,
    name : String
  )
  
  sealed trait Meal {
    def name:String
  }
  case object Breakfast extends Meal {
    def name():String = "Frokost"
  }
  
  case object Lunch extends Meal {
    def name():String = "Lunsj"
  }
  
  case object Dinner extends Meal {
    def name():String = "Middag"
  }
  
  case object Supper extends Meal {
    def name():String = "Kveldsmat"
  }
  
  case object MiddleMeal extends Meal {
    def name():String = "Mellom måltid"
  }
  
  val meals:Seq[Meal] = Seq(
    Breakfast, Lunch, Dinner, Supper, MiddleMeal
  )
  
}
