package encryption.exceptions;


public class PasswordEncryptionException extends Exception {
    public PasswordEncryptionException(String message, Throwable throwable){
        super(message, throwable);
    }
}
