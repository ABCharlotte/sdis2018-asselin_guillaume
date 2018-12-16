 Readme
 ======
 
 Pour lancer le serveur, lancer l'application spring. Pour le receveur, il faut lancer le receveurISS
 qui est abonné aux actualité du groupe ISS.
 
 
 
 
 
 
 SUJET
 =========
 
 TP Systèmes distribués
===========
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
