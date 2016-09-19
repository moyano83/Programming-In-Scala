package com.jmoyano

/**
  * Created by jm186111 on 03/04/2016.
  */
class CheckSum {
  var sum = 0
  def add(x:Int) {sum += x}
}

object CheckSum{


  def main(args:Array[String]):Unit = {
    println("Test sum")
    val check = new CheckSum
    check.add(3)
    check.add(5)
    println(check.sum)
  }
}
