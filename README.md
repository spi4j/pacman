## Projets Pacman du Ministère des Armées (France) ##

**Pacman** : outil permettant de générer du code s'appuyant sur les classes Java de Spi4J et sur des modèles définis dans l'application cible, afin de structurer le squelette technique de cette application.

**[Spi4J](https://github.com/spi4j/spi4j)** : framework ayant pour objectif de fournir une API simple pour les applications JavaEE, d'où son nom : Simple Programming Interface for JavaEE.
Spi4J est composé d'une partie principale et de modules optionnels. Une application d'exemple d'utilisation est incluse.

Ces projets sont opensource en licence Apache (ASL v2).

Ces projets ont été créés avec la participation de **[Bull]** et sont encore en cours en Mars 2022 (version courante : Pacman 2.2.1 & Spi4J 2.2.1).

### Principaux éléments techniques directeurs de l'architecture ###
  * Architecture JavaEE
  * Architecture MDA avec une génération de squelettes de code basée sur des modèles et des scripts de génération (Pacman)
  * Architecture SOA basée sur des services métiers, séparés des objets métiers (DTOs et Entities) portant l'état des données
  * Architecture en couches (business, matching, persistence, ui) et N-tiers physiques (client, serveur d'applications, base de données relationnelle)
  * L'API générée est volontairement simple et promeut les bonnes pratiques de développement pour les applications de gestion

### [Téléchargements](../../releases/latest) ###
  * Sources du projet Pacman (scripts [Acceleo](http://www.acceleo.org))
  * Update site Eclipse contenant les plugins packagés de Pacman

### [Documentations](https://github.com/spi4j/spi4j/releases/tag/Documentations) ###
  * Présentation d'introduction à Spi4J et Pacman
  * Documentations d'architecture technique et d'utilisation de Spi4J et Pacman
  * Formations

### Vidéos de démonstration d'utilisation des outils ###
  * [DSL Entity](https://drive.google.com/file/d/0B_CIqBpjpa16NTJzLUhSTnpKQzA/edit?usp=sharing)
  * [DSL SOA-DTO](https://drive.google.com/file/d/0B_CIqBpjpa16aUtqNE5KUGhWaU0/edit?usp=sharing)
  * [DSL Requirement](https://drive.google.com/file/d/0B_CIqBpjpa16dmt3S1ZtTWRmdHc/edit?usp=sharing)
  * [DSL SOA-Services](https://drive.google.com/file/d/0B_CIqBpjpa16V3VvTnNLUy1icTA/edit?usp=sharing)
  * [DSL Implémentation Requirement 1](https://drive.google.com/file/d/0B_CIqBpjpa16OXkxY3BVQkstMG8/edit?usp=sharing)
  * [DSL Implémentation Requirement 2](https://drive.google.com/file/d/0B_CIqBpjpa16bUxXOGtDTzRkRFU/edit?usp=sharing)

### Modélisation ###
Pour créer les modèles (Cinematic, SOA/DTO, Entity, Database, Requirements, Graal, State Machine & Interaction), le Ministère des Armées dispose d'un bundle Eclipse **Safr@n Studio**. Ce bundle inclut les designers graphiques et hiérarchiques pour créer les modèles ainsi qu'Acceleo pour la génération MDA. Les designers et les fichiers des DSL (méta-modèles) sur lesquels s’appuient les modèles sont publiés en opensource sur [gitHub/ObeoNetwork](https://github.com/ObeoNetwork/InformationSystem).

Si vous ne disposez pas de **Safr@n Studio**, il est possible d'utiliser le studio [IS Designer (Information System Designer)](https://www.isdesigner.org/)

