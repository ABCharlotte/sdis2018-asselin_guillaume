package eu.telecomnancy.championnat;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CompetitionsRepository repository){
        return args -> {
            //log.info("Preloading"+repository.save(new Competitions("CoupeEst", new ArrayList<Equipes>(new Equipes("France"),new Equipes("Belgique"),new Equipes("Luxembourg") ) ) ));
            log.info("Preloading"+repository.save(new Competitions("CoupeEst", new ListEquipes().put(new Equipes("France")).put(new Equipes("Belgique")).put(new Equipes("Luxembourg")) )));
            log.info("Preloading"+repository.save(new Competitions("RegionNancy", new ListEquipes().put(new Equipes("Nancy")).put(new Equipes("Laxou")).put(new Equipes("Villers")).put(new Equipes("Vandoeuvre")) )));
        };
    }
}
