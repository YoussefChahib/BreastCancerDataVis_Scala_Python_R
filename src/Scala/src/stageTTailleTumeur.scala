import com.cibo.evilplot.displayPlot
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._

import scala.io.Source

object stageTTailleTumeur {
  private def decompte(lines: Seq[String]): Seq[Seq[Double]] = {
    var t1 = List[Double]()
    var t2 = List[Double]()
    var t3 = List[Double]()
    var t4 = List[Double]()

    lines.tail.foreach { line =>
      val stage = line.split(";")(1)
      val taille = line.split(";")(8).toDouble
      if (stage == "T1") t1 = t1 :+ taille
      else if (stage == "T2") t2 = t2 :+ taille
      else if (stage == "T3") t3 = t3 :+ taille
      else if (stage == "T4") t4 = t4 :+ taille
      else ()
    }
    Seq[Seq[Double]](t1, t2, t3, t4)
  }
  def main(args: Array[String]): Unit = {

    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()

    val data = decompte(lines)
    displayPlot(BoxPlot(data)
      .standard(xLabels = Seq("T1", "T2","T3", "T4"))
      .yLabel("Mois survit")
      .xLabel("Stage T")
      .title("Relation entre le stage T et la taille de la tumeur")
      .render())

  }
}

