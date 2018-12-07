package eu.telecomnancy.championnat;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


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
    /*
    @GetMapping("/competitions")
    Resources<Resource<Competition>> all() {

        List<Resource<Competition>> competitions = repository.findAll().stream()
                .map(competition -> new Resource<>(competition,
                        linkTo(methodOn(CompetitionController.class).one(competition.getId())).withSelfRel(),
                        linkTo(methodOn(CompetitionController.class).all()).withRel("competitions")))
                .collect(Collectors.toList());

        return new Resources<>(competitions,
                linkTo(methodOn(CompetitionController.class).all()).withSelfRel());
    }*/
    @GetMapping("/competitions")
    Resources<Resource<Competition>> all() {

        List<Resource<Competition>> competitions = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(competitions,
                linkTo(methodOn(CompetitionController.class).all()).withSelfRel());
    }

    @PostMapping("/competitions")
    Competition newCompetition(@RequestBody Competition newCompetition){
        return repository.save(newCompetition);
    }

    //Single item
    //No RESTful
    /*@GetMapping("/competitions/{id}")
    Competition one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id));
    }*/
    @RequestMapping(value = "/competitions/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@GetMapping("/competitions/{id}")
    Resource<Competition> one(@PathVariable Long id){
        Competition competition = repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id));
        return new Resource<>(competition,
                linkTo(methodOn(CompetitionController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CompetitionController.class).all()).withRel("competitions"));
    }/*
    @GetMapping("/competitions/{id}")
    Resource<Competition> one(@PathVariable Long id) {

        Competition competition = repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id));

        return assembler.toResource(competition);
    }*/

    @PutMapping("/competitions/{id}")
    Competition remplaceCompetition(@RequestBody Competition newCompetition, @PathVariable Long id){
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
    }

    @DeleteMapping("/competitions/{id}")
    void deleteCompetition(@PathVariable Long id){
        repository.deleteById(id);
    }

}
