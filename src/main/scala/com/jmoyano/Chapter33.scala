package com.jmoyano

import scala.swing._
import scala.swing.event.ButtonClicked
/**
  * Created by jm186111 on 05/12/2016.
  */
object Chapter33 extends SimpleSwingApplication {
  //scala.swing.SimpleGUIApplication does not exists in the package scala.swing
    def top = new MainFrame {
      title = "First Swing App"

      val button = new Button {
        name = "buttonText"
        text = "Click me"
      }
      val label = new Label {
        text = "No button clicks registered"
      }
      contents = new BoxPanel(Orientation.Vertical) {
        contents += button
        contents += label
        border = Swing.EmptyBorder(30, 30, 10, 30)
      }

      listenTo(button)
      reactions += {
        case ButtonClicked(n) if n.name=="buttonText" => button.text = "Clicked!"; label.text = "Clicked"
      }
    }
}
