package eu.telecomnancy.championnat;

public class EquipeNotFoundException extends RuntimeException{
    public EquipeNotFoundException(Long id) {
        super("L'équipe "+id+" n'a pas été trouvée.");
    }
}
