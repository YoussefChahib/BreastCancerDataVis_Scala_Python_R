import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv("../CancerSeins.csv", sep=";")

plt.scatter(data["Mois survit"], data["Taille de la tumeur"])

plt.title("Relation entre l'age et la taille de la tumeur")
plt.xlabel("Mois survit")
plt.ylabel("Taille de la tumeur")

plt.show()
