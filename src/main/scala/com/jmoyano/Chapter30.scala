package com.jmoyano
import scala.actors.{Actor => a}
import a._
/**
  * Created by jm186111 on 05/12/2016.
  */
object Chapter30 {
  def main(args: Array[String]): Unit = {
    val actor = a.actor{
      println ("exists")
    }
  }
}
