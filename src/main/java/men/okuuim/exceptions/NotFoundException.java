package men.okuuim.exceptions;

public class NotFoundException extends RuntimeException {

    private String string;

    public NotFoundException(String str) {
        this.string = str;
    }

}
