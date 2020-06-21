package fr.cailliaud.rsl.restapi.web.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Champion not found !")
public class ChampionResourceNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ChampionResourceNotFound() {
        super("Champion not found !");
    }
}
