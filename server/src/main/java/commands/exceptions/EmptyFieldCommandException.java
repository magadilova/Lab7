package commands.exceptions;

public class EmptyFieldCommandException extends RuntimeException {
    public EmptyFieldCommandException(String message) {
        super(message);
    }
}
