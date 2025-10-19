package ifpb.bd2_crud.service;


import ifpb.bd2_crud.entity.Compra;
import ifpb.bd2_crud.entity.Produto;
import ifpb.bd2_crud.exception.CompraInvalidaException;
import ifpb.bd2_crud.exception.CompraNaoEncontradaException;
import ifpb.bd2_crud.exception.ProdutoNaoEncontradoException;
import ifpb.bd2_crud.repository.CompraRepository;
import ifpb.bd2_crud.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;

    public CompraService(CompraRepository compraRepository, ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
    }

    public Compra postCompra(Compra compra){
        if(compra == null) {
            throw new CompraInvalidaException();
        }
        return compraRepository.postCompra(compra);
    }

    public Compra postCompra(){
        return compraRepository.postCompra();
    }

    public Compra getCompra(Integer id){
        Compra compra = compraRepository.getCompra(id);
        if (compra == null) {
            throw new CompraNaoEncontradaException();
        }
        return compra;
    }

    public Compra putCompra(Integer id, Compra compra){
        if (compra == null) {
            throw new CompraNaoEncontradaException();
        }
        if (compra.getId() == null) {
            throw new CompraNaoEncontradaException();
        }
        return  compraRepository.putCompra(id, compra);
    }

    public void deleteCompra(Integer id){
        Compra compra = compraRepository.getCompra(id);
        if (compra == null){
            throw  new CompraNaoEncontradaException();
        }
        produtoRepository.deleteProduto(id);
    }

    @Transactional
    public Compra addProduto(Integer compraId, Integer produtoId) {
        Compra compra = compraRepository.getCompra(compraId);
        if (compra == null) {
            throw  new CompraNaoEncontradaException();
        }
        Produto produto = produtoRepository.getProduto(produtoId);
        if (produto == null) {
            throw new ProdutoNaoEncontradoException();
        }
        compra.addProduto(produto);
        compra.setValorCompra(compra.getValorTotal());
        return compra;
    }

    @Transactional
    public Compra removeProduto(int compraId, int produtoId) {
        Compra compra = compraRepository.getCompra(compraId);
        if (compra == null) {
            throw  new CompraNaoEncontradaException();
        }
        Produto produto = produtoRepository.getProduto(produtoId);
        if (produto == null) {
            throw new ProdutoNaoEncontradoException();
        }
        compra.removeProduto(produto);
        compra.setValorCompra(compra.getValorTotal());
        return compra;
    }

    public List<Compra> getCompras() {
        return compraRepository.getCompras();
    }
}
