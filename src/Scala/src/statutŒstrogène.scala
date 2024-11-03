import com.cibo.evilplot.displayPlot

import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._


import scala.io.Source

object statutŒstrogène {
  private def decompte(lines: Seq[String]): Seq[Double] = {
    var a = 0
    var b = 0
    lines.tail.foreach { line =>
      val stage = line.split(";")(5)
      if (stage == "Positif") a += 1
      else if (stage == "Négatif") b += 1
      else ()
    }
    Seq[Double](a, b)
  }

  def main(args: Array[String]): Unit = {

    // Ouvrir et copier les lines du fichier csv dans la variable lines
    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()


    val frequence = decompte(lines)
    val data = Seq("Positif" -> frequence(0), "Négatif" -> frequence(1))
    displayPlot(pie(data)
      .title("Statut en Œstrogène")
      .rightLegend(labels = Option(Seq("Positif (" + (frequence(0) * 100 / 4024).round + "%)", "Négatif (" + (frequence(1) * 100 / 4024).round + "%)")))
      .render()
    )
  }
}

