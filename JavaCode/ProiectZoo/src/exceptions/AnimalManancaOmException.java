package exceptions;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class AnimalManancaOmException extends Exception {

    public AnimalManancaOmException(String message) {
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }

}
