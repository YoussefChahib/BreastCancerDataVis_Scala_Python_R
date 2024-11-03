import com.cibo.evilplot.displayPlot
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._

import scala.io.Source

object stageMTailleTumeur {
  private def decompte(lines: Seq[String]): Seq[Seq[Double]] = {
    var m1 = List[Double]()
    var m2 = List[Double]()
    var m3 = List[Double]()
    var m4 = List[Double]()
    var m5 = List[Double]()

    lines.tail.foreach { line =>
      val stage = line.split(";")(3)
      val taille = line.split(";")(7).toDouble
      if (stage == "M1") m1 = m1 :+ taille
      else if (stage == "M2") m2 = m2 :+ taille
      else if (stage == "M3") m3 = m3 :+ taille
      else if (stage == "M4") m4 = m4 :+ taille
      else if (stage == "M5") m5 = m5 :+ taille
      else ()
    }
    Seq[Seq[Double]](m1, m2, m3, m4, m5)
  }
  def main(args: Array[String]): Unit = {

    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()

    val data = decompte(lines)
    displayPlot(BoxPlot(data)
      .standard(xLabels = Seq("M1", "M2","M3", "M4","M5"))
      .yLabel("Taille de la tumeur")
      .xLabel("Stage M")
      .title("Relation entre le stage M et la taille de la tumeur")
      .render())

  }
}

