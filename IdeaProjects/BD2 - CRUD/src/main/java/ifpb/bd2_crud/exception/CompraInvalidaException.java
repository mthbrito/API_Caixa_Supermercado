package ifpb.bd2_crud.exception;

public class CompraInvalidaException extends RuntimeException {

    public CompraInvalidaException() {
        super("Compra inválida!");
    }

    public CompraInvalidaException(String message) {
        super(message);
    }
}
