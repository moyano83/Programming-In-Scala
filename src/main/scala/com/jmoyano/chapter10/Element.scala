package com.jmoyano.chapter10


/**
  * Created by jm186111 on 05/10/2016.
  */
abstract class Element {
  def contents: Array[String]
  def height:Int = contents.length
  def width:Int = if(height == 0) 0 else contents.head.length
  def above(element:Element) = new ArrayElement (this.contents ++ element.contents) //++ concatenates two arrays
  def besides(that:Element) = new ArrayElement(for ((line1, line2) <- this.contents zip that.contents) yield line1 + line2)
}

class ArrayElement(val contents:Array[String] = Array("hello", "world")) extends Element{}

class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}

class UniformElement(
                      ch: Char,
                      override val width: Int,
                      override val height: Int
                    ) extends Element {
  private val line = ch.toString * width
  def contents = Array(s"$height", s"$line")
}

object Element {
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)
  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)
  def elem(line: String): Element =
    new LineElement(line)
}
