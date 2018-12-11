package eu.telecomnancy.championnat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.security.krb5.internal.ccache.CredentialsCache;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CompetitionRepository repository, MatchRepository matchRepository, EquipeRepository equipeRepository){


        return args -> {

            Equipe iss = new Equipe("ISS");
            Equipe il = new Equipe("IL");
            log.info("Preloading"+equipeRepository.save(iss));
            log.info("Preloading"+equipeRepository.save(il));
            Competition maCompet= new Competition("JIG");
            maCompet.addEquipe(iss);
            maCompet.addEquipe(il);
            log.info("Preloading"+repository.save(maCompet));
            matchRepository.save(new Match(il, iss,Status.EN_COURS, "02/09/18, 10:11"));

            Equipe equipe1 = new Equipe("Nancy");
            Equipe equipe2 = new Equipe("Laxou");
            log.info("Preloading"+equipeRepository.save(equipe1));
            log.info("Preloading"+equipeRepository.save(equipe2));
            matchRepository.save(new Match(equipe1, equipe2));
            Competition Compet= new Competition("RegionNancy");
            Compet.addEquipe(equipe1);
            Compet.addEquipe(equipe2);
            equipe1 = new Equipe("Villers");
            log.info("Preloading"+equipeRepository.save(equipe1));
            Compet.addEquipe(equipe1);
            equipe1 = new Equipe("Vandoeuvre");
            log.info("Preloading"+equipeRepository.save(equipe1));
            Compet.addEquipe(equipe1);
            //log.info("Preloading"+repository.save(new Competition("RegionNancy") ));
            log.info("Preloading"+repository.save(Compet));


            //log.info("Preloading"+repository.save(new Competition("CoupeEst", new ArrayList<Equipes>(new Equipes("France"),new Equipes("Belgique"),new Equipes("Luxembourg") ) ) ));
            log.info("Preloading"+repository.save(new Competition("CoupeEst") ));


            matchRepository.findAll().forEach(match -> {
                log.info("Preloaded " + match);
            });
            /*equipeRepository.findAll().forEach(equipe -> {
                log.info("Preloaded " + equipe);
            });*/
        };


    }

}
