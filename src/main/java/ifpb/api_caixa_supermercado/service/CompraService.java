package ifpb.api_caixa_supermercado.service;

import ifpb.api_caixa_supermercado.dto.CompraRequestDTO;
import ifpb.api_caixa_supermercado.dto.CompraResponseDTO;
import ifpb.api_caixa_supermercado.entity.Compra;
import ifpb.api_caixa_supermercado.entity.Produto;
import ifpb.api_caixa_supermercado.exception.CompraInvalidaException;
import ifpb.api_caixa_supermercado.exception.CompraNaoEncontradaException;
import ifpb.api_caixa_supermercado.mapper.CompraMapper;
import ifpb.api_caixa_supermercado.repository.CompraRepository;
import ifpb.api_caixa_supermercado.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static ifpb.api_caixa_supermercado.mapper.CompraMapper.toCompraResponseDTO;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;

    public CompraService(CompraRepository compraRepository, ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
    }

    public CompraResponseDTO salvarCompra(CompraRequestDTO compraRequestDTO){
        if (compraRequestDTO.produtosCompra().isEmpty()) {
            throw new CompraInvalidaException("Compra sem produtos");
        }
        List<Produto> produtos = compraRequestDTO.produtosCompra().stream()
                .map(produtoRepository::buscarProdutoPorId)
                .toList();
        Compra compra = new Compra(produtos, compraRequestDTO.formaPagamento());
        return toCompraResponseDTO(compraRepository.salvarCompra(compra));
    }

    public CompraResponseDTO buscarCompraPorId(Integer id){
        Compra compra = compraRepository.buscarCompraPorId(id);
        if (compra == null) {
            throw new CompraNaoEncontradaException("Compra nao encontrada");
        }
        return toCompraResponseDTO(compra);
    }

    public List<CompraResponseDTO> listarCompras() {
        List<Compra> compras = compraRepository.listarCompras();
        return compras.stream()
                .map(CompraMapper::toCompraResponseDTO)
                .toList();
    }

    public CompraResponseDTO atualizarCompra(Integer id, CompraRequestDTO compraRequestDTO){
        Compra compra = compraRepository.buscarCompraPorId(id);
        if (compra == null) {
            throw new CompraNaoEncontradaException("Compra nao encontrada");
        }
        List<Produto> produtos = compraRequestDTO.produtosCompra().stream()
                .map(produtoRepository::buscarProdutoPorId)
                .toList();
        compra.setProdutosCompra(produtos);
        compra.setFormaPagamento(compraRequestDTO.formaPagamento());
        compra.setValorCompra(compra.calcularValorTotal());
        return  toCompraResponseDTO(compraRepository.atualizarCompra(compra));
    }

    public void removerCompra(Integer id){
        Compra compra = compraRepository.buscarCompraPorId(id);
        if (compra == null) {
            throw new CompraNaoEncontradaException("Compra nao encontrada");
        }
        produtoRepository.removerProduto(id);
    }
}
