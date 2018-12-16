Utilisation de l'application 
 =
 Présentation
 ---
 Ce TP a été fait par Madame Charlotte ASSELIN-BOULLÉ (ISS) et Monsieur Charles GUILLAUME (ISS) en application des cours de Systèmes Distribués (SDIS) à TELECOM Nancy, encadrés par Monsieur F. CHAROY et Monsieur G. OSTER. 
 
 Le concept est d'avoir une application permettant de gérer un championnat. 
 Il existe différentes entités avec leur attributs propres : 
 - Les competitions, elles ont un identifiant, un nom, peuvent regrouper plusieurs Equipes identifiées par leur Id (dont on propose un accès via les liens), plusieurs matchs de statuts finis (avec liens) et un "classement" correspondant au nombre de points gagnés par une équipe (le nombre de points à l'indice i dans le classement correspond au nombre de point de l'équipe à l'indice i dans la liste des Ids des Equipes).
 - Les matchs : ont un identifiant unique, deux équipes : une à Domicile et une en extérieur. Pour les deux équipes on propose de visualiser leur nom, leur identifiant unique, ainsi que leur score. Les matchs ont un statut qui peut être PRÉVU, REPORTÉ, ANNULÉ, EN_COURS, PAUSE, FINI. Les transitions suivent une logique imposée : on peut uniquement mettre en pause et fini un match en_cours, annulé et reporté ne peuvent être fait que si le match est prévu. Enfin les matchs peuvent être indépendants où liés à une compétition (dans ce cas là le lien vers la compétition est proposé). Lorsqu'un match passe en fini, si il est lié à une compétition, l'identifiant du match s'ajoute dans la liste des matchs fini de la compétition et le score des deux équipes influent le classement : si les deux équipes sont à égalité elles gagent chacune 1 point, sinon le gagant gagne 2 points, le perdant en perd 2. 
 - Les équipes : elles sont là encore identifiées par un ID unique et possèdent un nom.  
 
 Abonnement 
 ---
 Pour lancer le serveur, lancer l'application spring. Pour le receveur, il faut lancer le receveurISS
 qui est abonné aux actualité du groupe ISS.
 
 
 Tests
 ---
 Pour tester notre code nous avons mis en place un script shell doubler de fichiers textes qui effectuent un comparatif entre la sortie qu'on obtient et la sortie attendue. 
 
 Pour le lancer il faut lancer l'application puis lancer le fichier `./src/test/tests.sh`
 
 
 SUJET
 =
 
 TP Systèmes distribués
---
A faire par groupe de 2

Le but du TP est de réaliser une API REST pour une application permettant de gérer les scores de
championnats sportifs. Les ressources sont les suivantes

````http request
/competitions/

/competitions/{id}/

/competitions/{id}/equipes/

/competitions/{id}/matches/

/competitions/{id}/classement/

/matches/

/matches/{id}/

/matches/{id}/

/equipes/

/equipes/{id}

/equipes/{id}/matches/

````

Les matchs ont un statut
````
PREVU|EN COURS|PAUSE|FINI|REPORTE|ANNULE
````
````json
"match" : {
  "domicile": {
   "self": "http://localhost/equipes/12"
  },
  "scoredom" : 2 
  "exterieur": {
      "self": "http://localhost/equipes/13"
     }
}
````
Une équipe joue à domicile et l'autre à l'exterieur.
Le score est un score simple (2-0) 


1 - Faites une implantation simple de cette API dont les données seront stockées dans une base relationnelle

*Vous pourrez vous inspirer de cet exemple pour cette implantation*
https://spring.io/guides/tutorials/bookmarks/

2 - On veut pouvoir s'abonner au résultats en live d'une journée de championnat.
Adaptez le service pour que lorsqu'un résultat est ajouté à la base, les clients intéressés par une des équipes concernées par le résultat
reçoivent une notification du résultat grâce à un pattern publish/subscribe mis en place avec RabbitMQ.
https://spring.io/guides/gs/messaging-rabbitmq/

3 - Ecrivez un simple client Java qui s'abonne à un topic et qui reçoit les informations et les affiche dans la console au fur et à mesure

Vous prendrez soin d'écrire des tests pour votre API soit simplement en utilisant des scripts avec curl ou en utilisant
des méthodes de tests plus avancées

````
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
````

Vous pouvez utiliser une de ces queues AMPQ plutôt que d'installer un serveur RabbitMQ

````
amqp://vbyrbmdw:tgA5fxMj1TucmDS8BfZ8GCSjaaxNIha2@buck.rmq.cloudamqp.com/vbyrbmdw
amqp://rbrilnwu:ImStIfzlSEiGk72iJIg1sRphhg6IoMrZ@swan.rmq.cloudamqp.com/rbrilnwu
````
