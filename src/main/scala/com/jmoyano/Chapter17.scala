package com.jmoyano

/**
  * Created by jm186111 on 02/11/2016.
  */
object Chapter17 {
  def main(arr:Array[String]) = {
    val arr = new Array[Int](5)
    arr(4)=3

    println(arr.mkString(","))
  }
}
