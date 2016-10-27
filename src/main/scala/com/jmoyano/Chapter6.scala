package com.jmoyano

/**
  * Created by jm186111 on 25/10/2016.
  */
class Chapter6 (x:Int){
  require(x>0)
  def xMember = x
  def this(x:Int, y:Int) = this(x+y)

  def `def` = 3

  def getDoubleDef = `def` * 2
}
