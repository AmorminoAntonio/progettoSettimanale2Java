package catalogoBibliotecario;

public class IsbnNonValidoException extends RuntimeException {
    public IsbnNonValidoException(String message) {
        super(message);
    }
}
