import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv("../CancerSeins.csv", sep=";")
stageT = data["Statut de la progestérone"].value_counts()
stageN = data["Statut en œstrogène"].value_counts()

fig, axes = plt.subplots(nrows=1, ncols=2, figsize=(15, 5))

axes[0].pie(stageT, labels=stageT.index, autopct='%1.1f%%', startangle=90)
axes[0].set_title("Statut de la progestérone")

axes[1].pie(stageN, labels=stageN.index, autopct='%1.1f%%', startangle=90)
axes[1].set_title("Statut en œstrogène")


plt.subplots_adjust(wspace=1)
fig.text(0.5, 0.1, 'Total: 4024', ha='center', fontsize=16, weight='bold')

plt.show()
