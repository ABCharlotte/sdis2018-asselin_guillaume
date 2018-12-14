package eu.telecomnancy.championnat;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class CompetitionResourceAssembler implements ResourceAssembler<Competition, Resource<Competition>> {

    @Override
    public Resource<Competition> toResource(Competition competition) {

        Resource res = new Resource<>(competition,
                linkTo(methodOn(CompetitionController.class).one(competition.getId())).withSelfRel(),
                linkTo(methodOn(CompetitionController.class).all()).withRel("competitions")/*,

                linkTo(methodOn(EquipeController.class).allOne(competition.getEquipesIds())).withRel("equipesParticipantes")*/
        );
        //int n=1;
        for (Long i : competition.getEquipesIds()){
            res.add(linkTo(methodOn(EquipeController.class).one(i)).withRel("EquipeParticipantes "+i));
            //n+=1;
        }
        //n=1;
        for (Long i : competition.getMatchesFinisId()){
            res.add(linkTo(methodOn(MatchController.class).one(i)).withRel("Matchs Finis "+i));
            //n+=1;
        }

        return res;
    }
}