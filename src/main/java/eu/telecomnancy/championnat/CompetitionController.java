package eu.telecomnancy.championnat;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class CompetitionController {

    private final CompetitionRepository repository;

    CompetitionController(CompetitionRepository repository){
        this.repository = repository;
    }

    //Agregate root
    @GetMapping("/competitions")
    List<Competition> all(){
        return repository.findAll();
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
    @GetMapping("/competitions/{id}")
    Resource<Competition> one(@PathVariable Long id){
        Competition competition = repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id));
        return new Resource<>(competition,
                linkTo(methodOn(CompetitionController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CompetitionController.class).all()).withRel("competitions"));
    }

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
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }

}
