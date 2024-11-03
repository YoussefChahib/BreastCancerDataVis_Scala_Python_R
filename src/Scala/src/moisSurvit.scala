import com.cibo.evilplot.displayPlot
import com.cibo.evilplot.colors.RGB
import com.cibo.evilplot.geometry.{Align, Drawable, Extent, Rect}
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.renderers.BarRenderer
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._

import scala.io.Source
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.apache.commons.math3.stat.StatUtils


object moisSurvit {
  private def decompte(lines: Seq[String]): Seq[Double] = {
    var a = 0
    var b = 0
    var c = 0
    var d = 0
    var e = 0
    lines.tail.foreach { line =>
      val mois = line.split(";")(8).toInt
      if (line.split(";")(9) == "Morte") {
        if (mois >= 0 && mois < 20) a += 1
        else if (mois >= 20 && mois < 40) b += 1
        else if (mois >= 40 && mois < 60) c += 1
        else if (mois >= 60 && mois < 80) d += 1
        else if (mois >= 80) e += 1
        else ()
      }
    }
    Seq[Double](a, b, c, d, e)
  }

  def main(args: Array[String]): Unit = {

    // Ouvrir et copier les lines du fichier csv dans la variable lines
    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()

    val col = lines.tail.map(_.split(";")(8).toDouble)
    val stats = new DescriptiveStatistics()
    col.foreach(stats.addValue)
    println("Moyenne: " + StatUtils.mean(col.toArray))
    println("Médiane: " + StatUtils.percentile(col.toArray, 50.0))
    println("Variance: " + stats.getVariance)
    println("Écart-type: " + stats.getStandardDeviation)
    println("Étendue: " + stats.getMax + " - " + stats.getMin + " = " + (stats.getMax - stats.getMin))

    val frequence = decompte(lines)

    val labels = Seq("de 0 à 20", "de 20 à 40", "de 40 à 60", "de 60 à 80", "de 80 à 107")
    val labeledByColor = new BarRenderer {
      override def render(plot: Plot, extent: Extent, category: Bar): Drawable = {
        val rect = Rect(extent)
        val color = RGB(151, 255, 140)
        Align.center(rect filled color).group
      }
    }
    displayPlot(BarChart.custom(frequence.map(Bar.apply),
        barRenderer = Some(labeledByColor)
      )
      .xLabel("Age")
      .yLabel("Fréquence")
      .title("Distribution des mois survit des personnes décédées")
      .standard(xLabels = labels)
      .hline(0)
      .render())
  }
}
