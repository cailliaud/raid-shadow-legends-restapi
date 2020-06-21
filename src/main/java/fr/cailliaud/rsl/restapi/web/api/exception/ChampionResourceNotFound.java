package fr.cailliaud.rsl.restapi.web.api.exception;

public class ChampionResourceNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ChampionResourceNotFound() {
        super("Champion not found !");
    }
}
