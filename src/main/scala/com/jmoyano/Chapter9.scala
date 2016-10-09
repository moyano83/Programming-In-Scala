package com.jmoyano

/**
  * Created by jm186111 on 04/10/2016.
  */
object Chapter9 {

  def innerFunction(x:Int) = {
    def inner() ={
      x+3
    }
    val result = inner()
    result
  }

  def useFunction(x:Int, sum:(String, Int) => String): String ={
    sum("e", x)
  }

  def main(args:Array[String])={
    println (innerFunction(5))

    val x = sum _
    //x(3,6) won't compile
    x(3,6,1)

    useFunction(5, (x, y) => x.concat(y.toString))
  }

  def sum(a:Int, b:Int, c:Int) = a+b+c
}