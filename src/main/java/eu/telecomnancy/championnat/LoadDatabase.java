package eu.telecomnancy.championnat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CompetitionsRepository repository){
        return args -> {
            //log.info("Preloading"+repository.save(new Competitions("CoupeEst", new ArrayList<Equipes>(new Equipes("France"),new Equipes("Belgique"),new Equipes("Luxembourg") ) ) ));
            /*log.info("Preloading"+repository.save(new Competitions("CoupeEst", new ListEquipes().put(new Equipe("France")).put(new Equipe("Belgique")).put(new Equipe("Luxembourg")) )));
            log.info("Preloading"+repository.save(new Competitions("RegionNancy", new ListEquipes().put(new Equipe("Nancy")).put(new Equipe("Laxou")).put(new Equipe("Villers")).put(new Equipe("Vandoeuvre")) )));
            */
            /*log.info("Preloading"+repository.save(new Competitions("CoupeEst", new Equipe("France"), new Equipe("Belgique") ) ));
            log.info("Preloading"+repository.save(new Equipe("New Team"));*/
            log.info("Preloading"+repository.save(new Competitions("CoupeEst") ));
            log.info("Preloading"+repository.save(new Competitions("RegionNancy") ));
        };
    }

}
