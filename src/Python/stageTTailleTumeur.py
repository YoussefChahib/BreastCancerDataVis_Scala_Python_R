# Importation des bibliothèques
import pandas as pd
import matplotlib.pyplot as plt

#  Ouverture du fichier csv
data = pd.read_csv("../CancerSeins.csv", sep=";")

# Création du plotbox et affichage
data.boxplot(column="Taille de la tumeur", by="Stage T", grid=True)
plt.suptitle("")
plt.xlabel("Stage T")
plt.ylabel("Taille de la tumeur")
plt.title("Relation entre le stage T et la taille de la tumeur")
plt.show()

