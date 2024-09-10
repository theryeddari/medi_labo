# MedicalLabo

**MedicalLabo** développée avec Spring Boot, est conçue pour faciliter la gestion des informations personnelles et médicales des patients par les praticiens et les organisateurs de rendez-vous médicaux. Elle offre diverses fonctionnalités permettant la visualisation, la mise à jour et l'ajout de données, ainsi que l'évaluation des risques de santé pour les patients, en particulier pour le diabète.

## Document de présentation fonctionnel et Technique

Vous trouverez ici le document [Document de présentation MedicalLabo ](https://github.com/theryeddari/medi_labo/blob/ccb95cd4bf99dc5f096c2c5a1fb6a63a70b0b8c7/Documentation%20technique%20et%20fonctionnelle%20Medical%20Labo.pdf)

Le document contient en autres :

- **Présentation du Projet** : Vue d'ensemble et objectifs.
- **Fonctionnalités** : Description des fonctionnalités principales.
- **Les vues et user stories** : attente graphique sommaire et les actions de l'utilisateur 
- **Architecture** : Schémas et description technique du système.
- **Configuration** : la configuration des diférents modules.
- **Tests et coverage** : Stratégies et outils de test avec les rapports.
- **Sécurité** : Mesures de sécurité et gestion des données.
- **Green Code** :Une section sur le green Code concernant les améliorations à apporté et globalement une reflexion sur cette pratique.

  Pour toute question ou besoin d’assistance, référez-vous aux sections de support du document.

## Prérequis

- **Java** : 21
- **Spring Boot** : 3.2.2
- **JUnit** : 5
- **Docker** 
- **Log4j2**
- **Jacoco** : pour la couverture de code
- **Surefire** : pour les rapports de tests
- **MangoDb**
- **MySQL**
  
## Installation

La branche Main est utilisée comme branche de présentation par défaut.

Pour toute utilisation du code, ajout ou modification, veuillez créer une nouvelle branche à partir de la branche Master, disponible dans le projet après avoir cloné le dépôt.

Vous devez télécharger le code source de la branch master. Ensuite, utilisez Docker Compose pour construire et exécuter les services à partir de ce code source.

Un modèle de fichier .env est fourni dans la branche Main. Veuillez le compléter avec les variables requises. Assurez-vous de placer ce fichier à la racine du projet

Si vous le souhaitez vous pouvez modifier les scripts d'initialisation des données des bases de données et présent dans le dossier init_scripts.

## Green Code

Le Green Code est une approche de développement visant à réduire l'empreinte écologique des applications en optimisant les ressources et en minimisant l'impact environnemental tout au long de leur cycle de vie. Dans ce projet, nous pouvons mettre en place diverses actions pour rendre notre code plus écoresponsable, notamment l'optimisation des requêtes, l'efficacité du code, la gestion des ressources serveurs, et l'utilisation d'une infrastructure cloud durable.

Pour plus de détails sur les actions spécifiques et les concepts abordés, veuillez consulter la section dédiée dans notre Documentation.


### Contact
Pour toute question, contactez moi.
