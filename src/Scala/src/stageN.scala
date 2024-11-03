import com.cibo.evilplot.displayPlot

import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._


import scala.io.Source

object stageN {
  private def decompte(lines: Seq[String]): Seq[Double] = {
    var a = 0
    var b = 0
    var c = 0
    lines.tail.foreach { line =>
      val stage = line.split(";")(2)
      if (stage == "N1") a += 1
      else if (stage == "N2") b += 1
      else if (stage == "N3") c += 1
      else ()
    }
    Seq[Double](a, b, c)
  }

  def main(args: Array[String]): Unit = {

    // Ouvrir et copier les lines du fichier csv dans la variable lines
    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()


    val frequence = decompte(lines)
    val data = Seq("N1" -> frequence(0), "N2" -> frequence(1), "N3" -> frequence(2))
    displayPlot(pie(data)
      .title("Pourcentage des patients de chaque stage N")
      .rightLegend(labels = Option(Seq("N1 (" + (frequence(0) * 100 / 4024).round + "%)", "N2 (" + (frequence(1) * 100 / 4024).round + "%)", "N3 (" + (frequence(2) * 100 / 4024).round + "%)")))
      .render()
    )
  }
}

