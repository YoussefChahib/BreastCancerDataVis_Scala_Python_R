donnees <- read.csv2('C:/Users/alouk/OneDrive/Bureau/ProjetAbourizk/CancerSeins.csv')

pdf(file = "C:/Users/alouk/OneDrive/Bureau/final.pdf",   
    width = 4, 
    height = 4) 
#analyse unidimensionnelle
#qst1
moyenneAge <- mean(donnees$Age)
print(moyenneAge)
médianeAge <- median(donnees$Age)
print(médianeAge)
écart_typeAge <- sd(donnees$Age)
print(écart_typeAge)
étendueAge <- range(donnees$Age)
print(étendueAge)
varianceAge <- var(donnees$Age)
print(varianceAge)

ggplot(donnees, aes(x= Age)) + 
  geom_histogram(binwidth = 10, fill = "darkolivegreen4", color = "black") +
  labs(title = "Distribution de l'âge",x = "Age",y = "Fréquence") +
  theme_light()
#qst2

# Compter le nombre de patients pour chaque stade T
table_T <- table(donnees$Stage.T)
print(table_T)
# Créer un diagramme cerculaire pour le stade T
pie(table_T, main = "Répartition des patients selon le stade T", col = rainbow(length(table_T)),
    labels = paste(names(table_T), ": ", table_T), cex = 0.8)

# Compter le nombre de patients pour chaque stade N
table_N <- table(donnees$Stage.N)
print(table_N)
# Créer un diagramme circulaire pour le stade N
pie(table_N, main = "Répartition des patients selon le stade N", col = rainbow(length(table_N)),
    labels = paste(names(table_N), ": ", table_N), cex = 0.8)

# Compter le nombre de patients pour chaque stade M
table_M <- table(donnees$Stage.M)
print(table_M)
# Créer un diagramme circulaire pour le stade M
pie(table_M, main = "Répartition des patients selon le stade M", col = rainbow(length(table_M)),
    labels = paste(names(table_M), ": ", table_M), cex = 0.8)
#qst3
table_diff <- table(donnees$Différenciation.cellulaire)
print(table_diff)

# Convertir les résultats en un data.frame
df_diff <- as.data.frame(table_diff)
colnames(df_diff) <- c("Type", "Nombre de patients")

# Créer un diagramme à barres avec ggplot2
ggplot(df_diff, aes(x = Type, y = `Nombre de patients`)) +
  geom_bar(stat = "identity", fill = "deepskyblue4", color = "black") +
  labs(title = "Nombre de patients selon la  différenciation cellulaire",
       x = "Type de différenciation", y = "Nombre de patients") +
  theme_minimal()

#qst4

table_O <- table(donnees$Statut.en.œstrogène)
print(table_O)

pie(table_O, main = "Répartition des patients par statut en œstrogène", col = rainbow(length(table_O)),
    labels = paste(names(table_O), ": ", table_O), cex = 0.8)

table_P <- table(donnees$Statut.de.la.progestérone)
print(table_P)

pie(table_P, main = "Répartition des patients par statut en progestérone", col = rainbow(length(table_P)),
    labels = paste(names(table_P), ": ", table_P), cex = 0.8)
#qst5

moyennetumor <- mean(donnees$Taille.de.la.tumeur)
print(moyennetumor)
médianetumor <- median(donnees$Taille.de.la.tumeur)
print(médianetumor)
écart_typetumor <- sd(donnees$Taille.de.la.tumeur)
print(écart_typetumor)
étenduetumor <- range(donnees$Taille.de.la.tumeur)
print(étenduetumor)
variancetumor <- var(donnees$Taille.de.la.tumeur)
print(variancetumor)

# Créer un histogramme pour visualiser la distribution
ggplot(donnees, aes(x = Taille.de.la.tumeur)) +
  geom_histogram(binwidth = 5, fill = "cyan4", color = "black", alpha = 0.7) +
  labs(title = "Distribution de la taille de la tumeur", x = "Taille de la tumeur", y = "Fréquence") +
  theme_minimal()

# Identifier les valeurs aberrantes
boxplot(donnees$Taille.de.la.tumeur, main = "Boxplot de la taille de la tumeur", ylab = "Taille de la tumeur")


#qst6
moyennesurv <- mean(donnees$Mois.survit)
print(moyennesurv)
médianesurv <- median(donnees$Mois.survit)
print(médianesurv)
écart_typesurv <- sd(donnees$Mois.survit)
print(écart_typesurv)
étenduesurv <- range(donnees$Mois.survit)
print(étenduesurv)
variancesurv <- var(donnees$Mois.survit)
print(variancesurv)

ggplot(donnees, aes(x = Mois.survit)) +
  geom_histogram(binwidth = 5, fill = "darksalmon", color = "black", alpha = 0.7) +
  labs(title = "Distribution de la durée de survie en mois", x = "Durée de survie en mois", y = "Fréquence") +
  theme_minimal()

# Identifier les valeurs aberrantes
boxplot(donnees$Mois.survit, main = "Boxplot de la durée de survie en mois", ylab = "Durée de survie en mois")

#qst7

# Compter le nombre de patients pour chaque statut
table_status <- table(donnees$Statut)
print(table_status)
# Convertir les résultats en un data.frame
df_status <- as.data.frame(table_status)
colnames(df_status) <- c("Statut", "Nombre de patients")

# Créer un diagramme à barres avec ggplot2
ggplot(df_status, aes(x = Statut, y = `Nombre de patients`)) +
  geom_bar(stat = "identity", fill = "darkslateblue", color = "black") +
  labs(title = "Nombre de patients par statut", x = "Statut", y = "Nombre de patients") +
  theme_minimal()

#analyse bidimensionnelle
#qst1
ggplot(donnees,aes(x = Age, y = Taille.de.la.tumeur)) +
  geom_point(shape = 16, color = "blue") +
  labs(title = "Relation entre l'age et la taille de la tumeur", x = "Age", y ="Taille de la tumeur")
#qst2
#stade T
df_tumor_stages <- data.frame(
  Stade = rep("T", times = nrow(donnees)),
  TumorSize = donnees$Taille.de.la.tumeur,
  Stage = donnees$Stage.T
)


ggplot(df_tumor_stages, aes(x = Stade, y = TumorSize, fill = Stage)) +
  geom_boxplot() +
  labs(title = "Relation entre la taille de la tumeur et le stade T",
       x = "Stade T", y = "Taille de la tumeur") +
  theme_minimal()
#stadeN
df_tumor_stages <- data.frame(
  Stade = rep("N", times = nrow(donnees)),
  TumorSize = donnees$Taille.de.la.tumeur,
  Stage = donnees$Stage.N
)

ggplot(df_tumor_stages, aes(x = Stade, y = TumorSize, fill = Stage)) +
  geom_boxplot() +
  labs(title = "Relation entre la taille de la tumeur et le stade N",
       x = "Stade N", y = "Taille de la tumeur") +
  theme_minimal()

#stade M
df_tumor_stages <- data.frame(
  Stade = rep("M", times = nrow(donnees)),
  TumorSize = donnees$Taille.de.la.tumeur,
  Stage = donnees$Stage.M
)

ggplot(df_tumor_stages, aes(x = Stade, y = TumorSize, fill = Stage)) +
  geom_boxplot() +
  labs(title = "Relation entre la taille de la tumeur et le stade M",
       x = "Stade.M", y = "Taille de la tumeur") +
  theme_minimal()

#qst 3
table_contingence1 <- table(donnees$Différenciation.cellulaire, donnees$Statut.en.œstrogène)
print(table_contingence1)

#qst 4
table_contingence2 <- table(donnees$Statut.de.la.progestérone, donnees$Statut.en.œstrogène)
print(table_contingence2)
#qst5
ggplot(donnees, aes(x = Taille.de.la.tumeur, y = Mois.survit)) +
  geom_point(color = "deeppink4") +
  labs(title = "Relation entre le mois de survie et la taille de la tumeur",
       x = "Taille de la tumeur", y = "Mois de survie") +
  theme_minimal()
#qst6
ggplot(donnees, aes(x = Statut, y = Mois.survit, fill = Statut)) +
  geom_boxplot() +
  labs(title = "Relation entre les mois de survie et le statut",
       x = "Statut", y = "Mois de survie") +
  theme_minimal()

dev.off()

shell.exec("C:/Users/alouk/OneDrive/final.pdf")

