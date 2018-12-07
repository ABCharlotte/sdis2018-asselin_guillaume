package eu.telecomnancy.championnat;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MatchResourceAssembler implements ResourceAssembler<Match, Resource<Match>> {

    @Override
    public Resource<Match> toResource(Match match) {

        // unconditional links to single-item resource and aggregate root
        Resource<Match> matchResource = new Resource<>(match,
                linkTo(methodOn(MatchController.class).one(match.getId())).withSelfRel(),
                linkTo(methodOn(MatchController.class).all()).withRel("matches")
        );

        //conditional links based on state of the match
        //REVU, EN_COURS, PAUSE, FINI, REPORTE, ANNULE;
        if(match.getStatus() == Status.EN_COURS){
            //peut être en pause, fini
            matchResource.add(
                    linkTo(methodOn(MatchController.class)
                        .fini(match.getId())).withRel("fini"));
            matchResource.add(
                    linkTo(methodOn(MatchController.class)
                            .pause(match.getId())).withRel("pause"));
        }
        else if (match.getStatus() == Status.PREVU){
            //peut être en_cours, reporté, annulé
            matchResource.add(
                    linkTo(methodOn(MatchController.class)
                            .enCours(match.getId())).withRel("en_cours"));
            matchResource.add(
                    linkTo(methodOn(MatchController.class)
                            .reporte(match.getId())).withRel("reporte"));
            matchResource.add(
                    linkTo(methodOn(MatchController.class)
                            .annule(match.getId())).withRel("annule"));
        }
        else if (match.getStatus() == Status.PAUSE) {
            //peut être relancé
            matchResource.add(
                    linkTo(methodOn(MatchController.class)
                            .enCours(match.getId())).withRel("en_cours"));
        }

        return matchResource;
    }
}