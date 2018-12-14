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

        return new Resource<>(competition,
                linkTo(methodOn(CompetitionController.class).one(competition.getId())).withSelfRel(),
                linkTo(methodOn(CompetitionController.class).all()).withRel("competitions")/*,
                linkTo(methodOn(EquipeController.class).one(competition.equipesIds[]))*/
        );
    }
}