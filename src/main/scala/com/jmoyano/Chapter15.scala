package com.jmoyano

/**
  * Created by jm186111 on 27/10/2016.
  */
class Chapter15 {

  val getOptionResult : Option[String] => Int =  {
    case Some(x) => 1
    case None => 0
  }

  val getAnyRef: Any => String = {
    case e: String => e
    case e: Seq[Any] => s"${e.length}"
    case _ => "?"
  }
  def getSomeValue(e:Some[String]) : String = e match{
    case Some(x) => x
    case None => "?"
  }
  val getInstanceObject = Chapter15()
}

object Chapter15 {
  def main(str:Array[String]) = {
    val a = new Chapter15
    assert (a.getAnyRef("Test this") == "9")
  }

  def apply() = new Chapter15
}
