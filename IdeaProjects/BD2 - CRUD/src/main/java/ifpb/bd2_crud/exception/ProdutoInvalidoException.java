package ifpb.bd2_crud.exception;

public class ProdutoInvalidoException extends RuntimeException {

    public ProdutoInvalidoException() {
        super("Produto inválido!");
    }

    public ProdutoInvalidoException(String message) {
        super(message);
    }
}
