package com.jmoyano

/**
  * Created by jm186111 on 01/11/2016.
  */
class Chapter16 {

  def reverseList[T](x:List[T]):List[T] ={
    x match {
      case List() => x
      case x1::xl => reverseList(xl):::x1
    }
  }

}
