package co.udea.hero.api.tourofhero.config.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(){

    }

    public BadRequestException(String mesagge){
        super(mesagge);
    }
}
