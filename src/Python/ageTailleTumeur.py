import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv("../CancerSeins.csv", sep=";")

plt.scatter(data["Age"], data["Taille de la tumeur"])

plt.title("Relation entre l'age et la taille de la tumeur")
plt.xlabel("Age")
plt.ylabel("Taille de la tumeur")

plt.show()
