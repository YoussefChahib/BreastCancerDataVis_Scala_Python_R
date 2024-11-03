import com.cibo.evilplot.displayPlot
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import com.cibo.evilplot.numeric._

import scala.io.Source

object ageTailleTumeur {
  def main(args: Array[String]): Unit = {

    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()

    var point = List[Point]()
    lines.tail.foreach { line =>
      point = point :+ Point(line.split(";")(0).toDouble, line.split(";")(7).toDouble)
    }
    displayPlot(ScatterPlot(point)
      .standard()
      .xLabel("Age")
      .yLabel("Taille de la tumeur")
      .rightLegend()
      .render())


  }
}

