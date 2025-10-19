package ifpb.bd2_crud.exception;

public class CompraNaoEncontradaException extends RuntimeException {

    public CompraNaoEncontradaException() {
        super("Compra não encontrada!");
    }

    public CompraNaoEncontradaException(String message) {
        super(message);
    }
}
