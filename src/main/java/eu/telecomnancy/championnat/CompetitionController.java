package eu.telecomnancy.championnat;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CompetitionController {

    private final CompetitionRepository repository;
    private final CompetitionResourceAssembler assembler;

    CompetitionController(CompetitionRepository repository,
                          CompetitionResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    //Agregate root
    @RequestMapping(value = "/competitions", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@GetMapping("/competitions")
    Resources<Resource<Competition>> all() {

        List<Resource<Competition>> competitions = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(competitions,
                linkTo(methodOn(CompetitionController.class).all()).withSelfRel());
    }/*@GetMapping("/competitions")
    Resources<Resource<Competition>> all() {

        List<Resource<Competition>> competitions = repository.findAll().stream()
                .map(competition -> new Resource<>(competition,
                        linkTo(methodOn(CompetitionController.class).one(competition.getId())).withSelfRel(),
                        linkTo(methodOn(CompetitionController.class).all()).withRel("competitions")))
                .collect(Collectors.toList());

        return new Resources<>(competitions,
                linkTo(methodOn(CompetitionController.class).all()).withSelfRel());
    }*/

    @PostMapping("/competitions")
    ResponseEntity<?> newCompetition(@RequestBody Competition newCompetition) throws URISyntaxException {
        Resource<Competition> resource = assembler.toResource(repository.save(newCompetition));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }/*@PostMapping("/competitions")
    Competition newCompetition(@RequestBody Competition newCompetition){
        return repository.save(newCompetition);
    }*/

    //Single item
    @RequestMapping(value = "/competitions/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@GetMapping("/competitions/{id}")
    Resource<Competition> one(@PathVariable Long id){
        Competition competition = repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id));
        /*return new Resource<>(competition,
                linkTo(methodOn(CompetitionController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CompetitionController.class).all()).withRel("competitions"));*/
        return assembler.toResource(competition);
    }//No RESTful
    /*@GetMapping("/competitions/{id}")
    Competition one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id));
    }*/

    @PutMapping("/competitions/{id}")
    ResponseEntity<?> replaceCompetition(@RequestBody Competition newCompetition, @PathVariable Long id) throws URISyntaxException{
        Competition updatedCompetition = repository.findById(id)
                .map(competition -> {
                    competition.setName(newCompetition.getName());
                    competition.setEquipesIds(newCompetition.getEquipesIds());
                    competition.setClassement(newCompetition.getClassement());
                    return repository.save(competition);
                })
                .orElseGet(() -> {
                    newCompetition.setId(id);
                    return repository.save(newCompetition);
                });
        Resource<Competition> resource = assembler.toResource(updatedCompetition);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }/*Competition remplaceCompetition(@RequestBody Competition newCompetition, @PathVariable Long id){
        return repository.findById(id)
                .map(competition -> {
                    competition.setName(newCompetition.getName());
                    competition.setEquipes(newCompetition.getEquipes());
                    return repository.save(competition);
                })
                .orElseGet(() -> {
                    newCompetition.setId(id);
                    return repository.save(newCompetition);
                });
    }*/

    @DeleteMapping("/competitions/{id}")
    ResponseEntity<?> deleteCompetition(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }/*
    void deleteCompetition(@PathVariable Long id){
        repository.deleteById(id);
    }*/

}
