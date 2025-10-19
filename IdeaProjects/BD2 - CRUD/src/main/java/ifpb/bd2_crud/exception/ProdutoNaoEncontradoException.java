package ifpb.bd2_crud.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado!");
    }

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
