package eu.telecomnancy.championnat;

public class CompetitionNotFoundException extends RuntimeException{
    public CompetitionNotFoundException(Long id) {
        super("La competition "+id+" n'a pas été trouvée.");
    }
}
