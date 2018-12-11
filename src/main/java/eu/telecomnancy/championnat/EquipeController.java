package eu.telecomnancy.championnat;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class EquipeController {

    private final EquipeRepository repository;
    private final EquipeResourceAssembler assembler;

    EquipeController(EquipeRepository repository,
                     EquipeResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    //Agregate root
    @RequestMapping(value = "/equipes", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //@GetMapping("/equipes")
    Resources<Resource<Equipe>> all() {

        List<Resource<Equipe>> equipes = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(equipes,
                linkTo(methodOn(EquipeController.class).all()).withSelfRel());
    }

    @PostMapping("/equipes")
    ResponseEntity<?> newEquipe(@RequestBody Equipe newEquipe) throws URISyntaxException {
        Resource<Equipe> resource = assembler.toResource(repository.save(newEquipe));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    //Single item
    @RequestMapping(value = "/equipes/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //@GetMapping("/equipes/{id}")
    Resource<Equipe> one(@PathVariable Long id) {
        Equipe equipe = repository.findById(id)
                .orElseThrow(() -> new EquipeNotFoundException(id));
        return assembler.toResource(equipe);
    }//No RESTful
    /*@GetMapping("/equipes/{id}")
    Equipe one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EquipeNotFoundException(id));
    }*/

    @PutMapping("/equipes/{id}")
    ResponseEntity<?> replaceEquipe(@RequestBody Equipe newEquipe, @PathVariable Long id) throws URISyntaxException {
        Equipe updatedEquipe = repository.findById(id)
                .map(equipe -> {
                    equipe.setName(newEquipe.getName());
                    return repository.save(equipe);
                })
                .orElseGet(() -> {
                    newEquipe.setId(id);
                    return repository.save(newEquipe);
                });
        Resource<Equipe> resource = assembler.toResource(updatedEquipe);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/equipes/{id}")
    ResponseEntity<?> deleteEquipe(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}