/**
  * Created by jm186111 on 31/07/2016.
  */
object Chapter7 {
  val value:String = ""
  println(
    value match{
      case "" => "esto1"
      case "esto" => "aquello"
    }
  )
  val collection = Array("Esto", "es", "una", "prueba")
  val filterCollection = for (
    item <- collection
    if (item.contains("a"))
  ) yield item.toUpperCase()
  println(filterCollection.mkString(","))
}