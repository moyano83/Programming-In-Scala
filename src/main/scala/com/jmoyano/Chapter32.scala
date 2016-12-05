package com.jmoyano
import scala.util.parsing.combinator._

/**
  * Created by jm186111 on 04/12/2016.
  */
class Chapter32 extends JavaTokenParsers {

  def expr: Parser[Any] = term~rep("+"~term | "-"~term)
  def term: Parser[Any] = factor~rep("*"~factor | "/"~factor)
  def factor: Parser[Any] = floatingPointNumber | "("~expr~")"
}

object Chapter32 extends Chapter32{
  def main(args: Array[String]) {
    println("input : "+ args(0))
    println(parseAll(expr, args(0)))
  }
}