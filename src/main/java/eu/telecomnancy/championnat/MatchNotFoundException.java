package eu.telecomnancy.championnat;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(Long id) {
        super("Le match "+id+" n'a pas été trouvé.");
    }
}
