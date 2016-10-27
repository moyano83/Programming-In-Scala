package com.jmoyano

/**
  * Created by jm186111 on 09/10/2016.
  */
package test{
  class TestCase
}

class Chapter13 {
  new _root_.com.jmoyano.test.TestCase
  //new _root_.test.TestCase Doesn't work
  import _root_.com.jmoyano.test.{TestCase => test}
  val tst = new test
}

