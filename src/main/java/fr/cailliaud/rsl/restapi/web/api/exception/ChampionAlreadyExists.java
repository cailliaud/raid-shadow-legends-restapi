package fr.cailliaud.rsl.restapi.web.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ChampionAlreadyExists extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ChampionAlreadyExists() {
        super("Champion already exists");
    }

}
