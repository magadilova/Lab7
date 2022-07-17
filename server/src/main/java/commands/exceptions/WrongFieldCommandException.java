package commands.exceptions;

public class WrongFieldCommandException extends RuntimeException {
    public WrongFieldCommandException(String message) {
        super(message);
    }
}
