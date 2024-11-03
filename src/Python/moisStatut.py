import pandas as pd
import matplotlib.pyplot as plt
data = pd.read_csv("../CancerSeins.csv", sep=";")
data.boxplot(column="Mois survit", by="Statut", grid=True)
plt.suptitle("")
plt.xlabel("Statut")
plt.ylabel("Mois survit")
plt.title("Relation entre le statut et le nombre de mois survit")
plt.show()
