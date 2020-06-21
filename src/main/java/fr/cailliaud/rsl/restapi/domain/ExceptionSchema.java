package fr.cailliaud.rsl.restapi.domain;

public class ExceptionSchema {

    private String message;
    private String details;


    protected ExceptionSchema() {}

    public ExceptionSchema(
            String message, String details, String hint, String nextActions, String support) {
        this.message = message;
        this.details = details;
    }
}
