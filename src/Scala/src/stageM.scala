import com.cibo.evilplot.displayPlot

import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._


import scala.io.Source

object stageM {
  private def decompte(lines: Seq[String]): Seq[Double] = {
    var a = 0
    var b = 0
    var c = 0
    var d = 0
    var e = 0
    lines.tail.foreach { line =>
      val stage = line.split(";")(3)
      if (stage == "M1") a += 1
      else if (stage == "M2") b += 1
      else if (stage == "M3") c += 1
      else if (stage == "M4") d += 1
      else if (stage == "M5") e += 1
      else ()
    }
    Seq[Double](a, b, c, d, e)
  }

  def main(args: Array[String]): Unit = {

    // Ouvrir et copier les lines du fichier csv dans la variable lines
    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()


    val frequence = decompte(lines)
    val data = Seq("M1" -> frequence(0), "M2" -> frequence(1), "M3" -> frequence(2), "M4" -> frequence(3), "M5" -> frequence(4))
    displayPlot(pie(data)
      .title("Pourcentage des patients de chaque stage M")
      .rightLegend(labels = Option(Seq("M1 (" + (frequence(0) * 100 / 4024).round + "%)", "M2 (" + (frequence(1) * 100 / 4024).round + "%)", "M3 (" + (frequence(2) * 100 / 4024).round + "%)", "M4 (" + (frequence(3) * 100 / 4024).round + "%)", "M5 (" + (frequence(4) * 100 / 4024).round + "%)")))
      .render()
    )
  }
}

