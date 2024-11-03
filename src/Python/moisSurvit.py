import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv("../CancerSeins.csv", sep=";")
print("Moyenne: ", data["Mois survit"].mean())
print("Médian: ", data["Mois survit"].median())
print("Écart-type: ", data["Mois survit"].std())
print("Étendue: ", data["Mois survit"].max(), "-", data["Mois survit"].min(), "=", data["Mois survit"].max() - data["Mois survit"].min())
print("Variance: ", data["Mois survit"].var())
personnesDécédées = data[data["Statut"] == "Morte"]
plt.hist(personnesDécédées["Mois survit"], bins=5, color="blue", edgecolor="black")
plt.title("Distribution des mois survit des personnes décédées")
plt.xlabel("Mois survit")
plt.ylabel("Fréquence")
plt.show()
