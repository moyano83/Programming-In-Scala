package com.jmoyano

/**
  * Created by jm186111 on 25/10/2016.
  */

/**
  * Created by jm186111 on 27/07/2016.
  */

class Rational(n:Int, d:Int){
  private val num:Int =n
  require( num != null )
  val den:Int =d
  def this(x:Int) = this(x,1)
  def +(r:Rational) = new Rational(this.num + r.num, this.den + r.den)
}

object Chapter6 {
  implicit def intToRational(x: Int) = new Rational(x)

  val a = new Rational(1, 2)
  val b = new Rational(3, 2)

  println(3 + a + b)
}

class Chapter6 (x:Int){
  require(x>0)
  def xMember = x
  def this(x:Int, y:Int) = this(x+y)

  def `def` = 3

  def getDoubleDef = `def` * 2
}
