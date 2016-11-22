package com.jmoyano

/**
  * Created by jm186111 on 22/11/2016.
  */
class Chapter23 {
  val books: List[Book] =
    List(
      Book(
        "Structure and Interpretation of Computer Programs",
        "Abelson, Harold", "Sussman, Gerald J."
      ), Book(
        "Principles of Compiler Design",
        "Aho, Alfred", "Ullman, Jeffrey"
      ),
      Book(
        "Programming in Modula-2",
        "Wirth, Niklaus"
      ), Book(
        "Elements of ML Programming",
        "Ullman, Jeffrey"
      ), Book(
        "The Java Language Specification", "Gosling, James",
        "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
      )
    )

  def findGosling = for{
    book<-books;
    c = book.authors;
    author <- c
    if author.contains("Gosling")
  } yield book.title

}

object Chapter23{
  def main(args:Array[String]) = {
    new Chapter23().findGosling.foreach(f => println(f))
  }
}
case class Book(title: String, authors: String*)