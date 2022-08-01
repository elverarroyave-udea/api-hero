package co.udea.hero.api.tourofhero.config.exception;

public class NotFoundException  extends RuntimeException{

    public NotFoundException(){

    }

    public NotFoundException(String message){
        super(message);
    }
}
