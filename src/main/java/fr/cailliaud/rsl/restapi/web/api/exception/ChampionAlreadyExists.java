package fr.cailliaud.rsl.restapi.web.api.exception;

public class ChampionAlreadyExists extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ChampionAlreadyExists() {
        super("Champion Name already exists");
    }
}
