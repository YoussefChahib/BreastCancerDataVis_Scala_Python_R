import com.cibo.evilplot.displayPlot

import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._


import scala.io.Source

object stageT {
  private def decompte(lines: Seq[String]): Seq[Double] = {
    var a = 0
    var b = 0
    var c = 0
    var d = 0
    lines.tail.foreach { line =>
      val stage = line.split(";")(1)
      if (stage == "T1") a += 1
      else if (stage == "T2") b += 1
      else if (stage == "T3") c += 1
      else if (stage == "T4") d += 1
      else ()
    }
    Seq[Double](a, b, c, d)
  }

  def main(args: Array[String]): Unit = {

    // Ouvrir et copier les lines du fichier csv dans la variable lines
    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()


    val frequence = decompte(lines)
    val data = Seq("T1" -> frequence(0), "T2" -> frequence(1), "T3" -> frequence(2), "T4" -> frequence(3))
    displayPlot(pie(data)
      .title("Pourcentage des patients de chaque stage T")
      .rightLegend(labels = Option(Seq("T1 (" + (frequence(0) * 100 / 4024).round + "%)", "T2 (" + (frequence(1) * 100 / 4024).round + "%)", "T3 (" + (frequence(2) * 100 / 4024).round + "%)", "T4 (" + (frequence(3) * 100 / 4024).round + "%)")))

      .render()
    )
  }
}

