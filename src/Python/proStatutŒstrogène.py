import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns


data = pd.read_csv("../CancerSeins.csv", sep=";")
tableau_contingence = pd.crosstab(data["Statut en œstrogène"], data["Statut de la progestérone"])
print(tableau_contingence)
plt.figure(figsize=(8, 6))
sns.heatmap(tableau_contingence, annot=True, cmap='YlGnBu', fmt='g')

# Ajouter des titres
plt.title('Tableau de Contingence')
plt.xlabel("Statut en œstrogène")
plt.ylabel("Statut de la progestérone")

plt.show()
