package eu.telecomnancy.championnat;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MatchController {
    private final MatchRepository matchRepository;
    private final MatchResourceAssembler assembler;
    private final CompetitionRepository competRepository;

    MatchController(MatchRepository matchRepository, MatchResourceAssembler assembler, CompetitionRepository competRepository1) {
        this.matchRepository = matchRepository;
        this.assembler = assembler;
        this.competRepository = competRepository1;
    }

    @RequestMapping(value = "/matches", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@GetMapping("/competitions")
    Resources<Resource<Match>> all() {
        List<Resource<Match>> matches = matchRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(matches,
                linkTo(methodOn(MatchController.class).all()).withSelfRel());
    }

    @RequestMapping(value = "/matches/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@GetMapping("/competitions")
    Resource<Match> one(@PathVariable Long id) {
        return assembler.toResource(
                matchRepository.findById(id)
                        .orElseThrow(() -> new MatchNotFoundException(id)));
    }

    @PostMapping("/matches")
    ResponseEntity<Resource<Match>> newOrder(@RequestBody Match match) {

        match.setStatus(Status.PREVU);
        Match newMatch = matchRepository.save(match);

        return ResponseEntity
                .created(linkTo(methodOn(MatchController.class).one(newMatch.getId())).toUri())
                .body(assembler.toResource(newMatch));
    }

    //EN COURS --> PAUSE, FINI
    // PREVU --> EN_COURS, REPORTE, ANNULE;
    @DeleteMapping("/matches/{id}/annule")
    ResponseEntity<ResourceSupport> annule(@PathVariable Long id){
        Match match = matchRepository.findById(id).orElseThrow(()->new MatchNotFoundException(id));
        if(match.getStatus()==Status.PREVU){
            match.setStatus(Status.ANNULE);
            return ResponseEntity.ok(assembler.toResource(matchRepository.save(match)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed","Vous ne pouvez pas annuler un match déjà démarré ou fini, or celui-ci est "+match.getStatus()+"."));
    }

    @RequestMapping(value = "/matches/{id}/pause", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@PutMapping("/match/{id}/pause")
    ResponseEntity<ResourceSupport> pause(@PathVariable Long id){
        Match match = matchRepository.findById(id).orElseThrow(()->new MatchNotFoundException(id));
        if(match.getStatus()==Status.EN_COURS){
            match.setStatus(Status.PAUSE);
            return ResponseEntity.ok(assembler.toResource(matchRepository.save(match)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed","Vous ne pouvez pas mettre en pause un match qui n'est pas en cours, or celui-ci est "+match.getStatus()+"."));
    }

    @RequestMapping(value = "/matches/{id}/fini", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@PutMapping("/match/{id}/fini")
    ResponseEntity<ResourceSupport> fini(@PathVariable Long id){
        Match match = matchRepository.findById(id).orElseThrow(()->new MatchNotFoundException(id));
        if(match.getStatus()==Status.EN_COURS){
            match.setStatus(Status.FINI);

            if (match.getCompetitionId()!=null){
                Competition compet = competRepository.findById(match.getCompetitionId()).orElseThrow(()->new MatchNotFoundException(match.getCompetitionId()));
                if (match.getScoreDom() > match.getScoreGuest()) {
                    compet.majClassement(match.getDomicile(), match.getExterieur(), Boolean.FALSE);
                } else {
                    compet.majClassement(match.getExterieur(), match.getDomicile(), match.getScoreDom() == match.getScoreGuest());
                }
            }
            return ResponseEntity.ok(assembler.toResource(matchRepository.save(match)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed","Seul un match en cours pour être considéré comme fini, or celui-ci est "+match.getStatus()+"."));
    }

    @RequestMapping(value = "/matches/{id}/en_cours", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@PutMapping("/match/{id}/en_cours")
    ResponseEntity<ResourceSupport> enCours(@PathVariable Long id){
        Match match = matchRepository.findById(id).orElseThrow(()->new MatchNotFoundException(id));
        if(match.getStatus()==Status.PREVU || match.getStatus()==Status.PAUSE){
            match.setStatus(Status.EN_COURS);
            return ResponseEntity.ok(assembler.toResource(matchRepository.save(match)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed","Seul un match qui était prévu ou en pause peut être (re)lancé, or celui-ci est "+match.getStatus()+"."));
    }

    @RequestMapping(value = "/matches/{id}/reporte", method = GET, produces = MediaType.APPLICATION_JSON_VALUE) //@PutMapping("/match/{id}/reporte")
    ResponseEntity<ResourceSupport> reporte(@PathVariable Long id){
        Match match = matchRepository.findById(id).orElseThrow(()->new MatchNotFoundException(id));
        if(match.getStatus()==Status.PREVU){
            match.setStatus(Status.REPORTE);
            return ResponseEntity.ok(assembler.toResource(matchRepository.save(match)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed","Seul un match prévu qui n'a pas déjà été démarré peut être reporté, or celui-ci est "+match.getStatus()+"."));
    }

}
