package eu.telecomnancy.championnat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChampionnatApplication {

    public static void main(String... args) {
        SpringApplication.run(ChampionnatApplication.class, args);
    }

}
/* ***En lignes de commandes ***
-- lancer :
curl -v localhost:8080/competitions
-- ajout :
curl -X POST localhost:8080/competitions -H 'Content-type:application/json' -d '{"name": "CompetitionDeTest"}'
-- changement
curl -X PUT localhost:8080/competitions/3 -H 'Content-type:application/json' -d '{"name": "Competition_De_Test"}'
-- suppression
curl -X DELETE localhost:8080/competitions/3
*/