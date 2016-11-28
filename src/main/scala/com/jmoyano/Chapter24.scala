package com.jmoyano

/**
  * Created by jm186111 on 28/11/2016.
  */
class Chapter24 {
  def unapply(arg: String): Option[String] = {
    if (arg.length > 5)
      Some(">5")
    else
      None
  }
}
