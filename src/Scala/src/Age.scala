import com.cibo.evilplot.displayPlot
import com.cibo.evilplot.colors.RGB
import com.cibo.evilplot.geometry.{Align, Drawable, Extent, Rect}
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.renderers.BarRenderer
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import scala.io.Source
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.apache.commons.math3.stat.StatUtils

object Age {
  private def decompte(lines: Seq[String]): Seq[Double] = {
    var a = 0
    var b = 0
    var c = 0
    var d = 0
    var e = 0
    var f = 0
    var g = 0
    var h = 0
    lines.tail.foreach { line =>
      val age = line.split(";")(0).toInt
      if (age >= 30 && age < 35) a += 1
      else if (age >= 35 && age < 40) b += 1
      else if (age >= 40 && age < 45) c += 1
      else if (age >= 45 && age < 50) d += 1
      else if (age >= 50 && age < 55) e += 1
      else if (age >= 55 && age < 60) f += 1
      else if (age >= 60 && age < 65) g += 1
      else if (age >= 65 && age < 70) h += 1
      else ()
    }
    Seq[Double](a, b, c, d, e, f, g, h)
  }

  def main(args: Array[String]): Unit = {
    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()
    val col = lines.tail.map(_.split(";")(0).toDouble)
    val stats = new DescriptiveStatistics()
    col.foreach(stats.addValue)
    println("Moyenne: " + StatUtils.mean(col.toArray))
    println("Médiane: " + StatUtils.percentile(col.toArray, 50.0))
    println("Variance: " + stats.getVariance)
    println("Écart-type: " + stats.getStandardDeviation)
    println("Étendue: " + stats.getMax + " - " + stats.getMin + " = " + (stats.getMax - stats.getMin))
    val frequence = decompte(lines)
    val labels = Seq("de 30 à 35", "de 35 à 40", "de 40 à 45", "de 45 à 50", "de 50 à 55", "de 55 à 60", "de 60 à 65", "de 65 à 70")
    val labeledByColor = new BarRenderer {
      override def render(plot: Plot, extent: Extent, category: Bar): Drawable = {
        val rect = Rect(extent)
        val color = RGB(234, 81, 74)
        Align.center(rect filled color).group
      }
    }
    displayPlot(BarChart.custom(frequence.map(Bar.apply), barRenderer = Some(labeledByColor))
      .xLabel("Age").yLabel("Fréquence").title("Distribution de l'age").standard(xLabels = labels).hline(0).render())
  }
}
      