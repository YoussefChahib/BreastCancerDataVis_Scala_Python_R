import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv("../CancerSeins.csv", sep=";")
stageT = data["Stage T"].value_counts()
stageN = data["Stage N"].value_counts()
stageM = data["Stage M"].value_counts()

fig, axes = plt.subplots(nrows=1, ncols=3, figsize=(15, 5))

axes[0].pie(stageT, labels=stageT.index, autopct='%1.1f%%', startangle=90)
axes[0].set_title("Pourcentage de patients pour chaque stage T")

axes[1].pie(stageN, labels=stageN.index, autopct='%1.1f%%', startangle=90)
axes[1].set_title("Pourcentage de patients pour chaque stage N")

axes[2].pie(stageM, labels=stageM.index, autopct='%1.1f%%', startangle=90)
axes[2].set_title("Pourcentage de patients pour chaque stage M")

plt.subplots_adjust(wspace=1)
fig.text(0.5, 0.1, 'Total: 4024', ha='center', fontsize=16, weight='bold')

plt.show()
