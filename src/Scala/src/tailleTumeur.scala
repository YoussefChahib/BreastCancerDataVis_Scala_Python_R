import com.cibo.evilplot.displayPlot
import com.cibo.evilplot.colors.RGB
import com.cibo.evilplot.geometry.{Align, Drawable, Extent, Rect}
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.renderers.BarRenderer
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.apache.commons.math3.stat.StatUtils

import scala.io.Source


object tailleTumeur {
  private def decompte(lines: Seq[String]): Seq[Double] = {
    var a = 0
    var b = 0
    var c = 0
    var d = 0
    var e = 0
    var f = 0
    var g = 0
    lines.tail.foreach { line =>
      val taille = line.split(";")(7).toInt
      if (taille >= 0 && taille < 20) a += 1
      else if (taille >= 20 && taille < 40) b += 1
      else if (taille >= 40 && taille < 60) c += 1
      else if (taille >= 60 && taille < 80) d += 1
      else if (taille >= 80 && taille < 100) e += 1
      else if (taille >= 100 && taille < 120) f += 1
      else if (taille >= 120 && taille < 140) g += 1
      else ()
    }
    Seq[Double](a, b, c, d, e, f, g)
  }

  def main(args: Array[String]): Unit = {

    // Ouvrir et copier les lines du fichier csv dans la variable lines
    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()

    val col = lines.tail.map(_.split(";")(7).toDouble)
    val stats = new DescriptiveStatistics()
    col.foreach(stats.addValue)
    println("Moyenne: "+ StatUtils.mean(col.toArray))
    println("Médiane: "+ StatUtils.percentile(col.toArray, 50.0))
    println("Variance: "+ stats.getVariance)
    println("Écart-type: "+ stats.getStandardDeviation)
    println("Étendue: "+ stats.getMax + " - " + stats.getMin + " = "+ (stats.getMax - stats.getMin))

    val frequence = decompte(lines)


    val labels = Seq("de 0 à 20", "de 20 à 40", "de 40 à 60", "de 60 à 80", "de 80 à 100", "de 100 à 120", "de 120 à 140")
    val labeledByColor = new BarRenderer {
      override def render(plot: Plot, extent: Extent, category: Bar): Drawable = {
        val rect = Rect(extent)
        val color = RGB(130, 153, 255)
        Align.center(rect filled color).group
      }
    }
    displayPlot(BarChart.custom(frequence.map(Bar.apply),
        barRenderer = Some(labeledByColor)
      )
      .xLabel("Age")
      .yLabel("Fréquence")
      .title("Distribution de la taille de la tumeur")
      .standard(xLabels = labels)
      .hline(0)
      .render())
  }
}
