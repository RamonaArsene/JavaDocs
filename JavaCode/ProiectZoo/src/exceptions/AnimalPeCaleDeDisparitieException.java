package exceptions;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class AnimalPeCaleDeDisparitieException extends Exception{
    public AnimalPeCaleDeDisparitieException(String message) {
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }

}
