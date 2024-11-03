//  Importation des bibliothèques
import com.cibo.evilplot.displayPlot
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import scala.io.Source

object stageNTailleTumeur {

  // Création des listes qui contiennent la taille de la tumeur groupés par le stade N
  private def decompte(lines: Seq[String]): Seq[Seq[Double]] = {
    var n1 = List[Double]()
    var n2 = List[Double]()
    var n3 = List[Double]()

    lines.tail.foreach { line =>
      val stage = line.split(";")(2)
      val taille = line.split(";")(7).toDouble
      if (stage == "N1") n1 = n1 :+ taille
      else if (stage == "N2") n2 = n2 :+ taille
      else if (stage == "N3") n3 = n3 :+ taille
      else ()
    }
    Seq[Seq[Double]](n1, n2, n3)
  }
  def main(args: Array[String]): Unit = {
    // Ouverture et lecture du fichier csv dans une variable
    val file = Source.fromFile("/Users/samy/Desktop/abourezk/CancerSeins.csv")
    val lines = file.getLines().toSeq
    file.close()

    val data = decompte(lines)

    // Création et affichagedu Boxplot
    displayPlot(BoxPlot(data)
      .standard(xLabels = Seq("N1", "N2","N3"))
      .yLabel("Taille de la tumeur")
      .xLabel("Stage N")
      .title("Relation entre le stage N et la taille de la tumeur")
      .render())

  }
}

