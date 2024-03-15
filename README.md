
# PROJET WEB : E-commerce J2EE

Rendu de projet de dev-web en __Ing-2 04/12/2023__.  

Ce projet est réalisé dans le cadre de la matière "J2EE" au premier semestre de la deuxième année de formation d'ingénieur en informatique de *CY Tech*.   

Ce site est un e-commerce fait avec __JAVA JEE__, __JDBC__ et __MySQL__. 

Sur ce e-commerce vous pouvez trouver tout type de produits un peu comme Amazon ou Ebay



## Installation

Instructions pour utiliser le site :
* Installer Apache Tomcat v10
* Installer la derniere version jdk java
* Mettre à jour les dépendances

## Run Configurations
- Tomcat Server > Application server : Tomcat <version>
- Before Launch add:
  - Build Artifacts : 'Market_Place:war_exploded'
- Deployment :
  - Deploy at the server startup add :
    - 'Market_Place:war_exploded'
  - Application context : '/'
  - Before Launch add:
    - 'Market_Place:war_exploded'
  

## DataBase MySQL
# /sql
Dans l'ordre lancer le script 'total' :

Si problèmes lors de l'execution, exécuter dans l'odre les tables individuellement dans 'total':
1. user
2. products
3. orders
4. cart


## API Reference

#### DBConnect
# /DB/DBConnect
- Ici qu'il faudra déclarer vos variables d'environnement pour la DB
- Penser à changer le password de votre bdd si ce n'est pas celui par défaut
- Default Password : password&2

# Admin
- Mail : jee890703@gmail.com
- Password : test
# Vendeur
- Mail : vendeur@vendeur.com
- Password : test



## Authors

- [@Adrien](https://github.com/Drien95)
- [@Romain](https://github.com/guenneaur)
- [@Alexandre](https://github.com/Drien95)
- [@Riwan](https://github.com/RiwanAST)


