import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv("../CancerSeins.csv", sep=";")
print("Moyenne: ", data["Taille de la tumeur"].mean())
print("Médian: ", data["Taille de la tumeur"].median())
print("Écart-type: ", data["Taille de la tumeur"].std())
print("Étendue: ", data["Taille de la tumeur"].max(), "-", data["Taille de la tumeur"].min(), "=", data["Taille de la tumeur"].max() - data["Taille de la tumeur"].min())
print("Variance: ", data["Taille de la tumeur"].var())
plt.hist(data["Taille de la tumeur"], bins=7, color="blanchedalmond", edgecolor="black")
plt.title("Distribution de la taille de la tumeur")
plt.xlabel("Taille de la tumeur")
plt.ylabel("Fréquence")
plt.show()
