# Importation des bibliothèques
import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv("../CancerSeins.csv", sep=";")


data.boxplot(column="Taille de la tumeur", by="Stage M", grid=True)

plt.suptitle("")
plt.xlabel("Stage M")
plt.ylabel("Taille de la tumeur")
plt.title("Relation entre le stage M et la taille de la tumeur")


plt.show()
