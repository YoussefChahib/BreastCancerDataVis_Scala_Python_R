import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv("../CancerSeins.csv", sep=";")
print("Moyenne: ", data["Age"].mean())
print("Médian: ", data["Age"].median())
print("Écart-type: ", data["Age"].std())
print("Étendue: ", data["Age"].max(), "-", data["Age"].min(), "=", data["Age"].max() - data["Age"].min())
print("Variance: ", data["Age"].var())
plt.hist(data["Age"], bins=8, color="aquamarine", edgecolor="black")
plt.title("Distribution de l'age")
plt.xlabel("Age")
plt.ylabel("Fréquence")
plt.show()
