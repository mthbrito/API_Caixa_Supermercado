package ifpb.api_caixa_supermercado.mapper;

import ifpb.api_caixa_supermercado.dto.CompraRequestDTO;
import ifpb.api_caixa_supermercado.dto.CompraResponseDTO;
import ifpb.api_caixa_supermercado.dto.ProdutoResponseDTO;
import ifpb.api_caixa_supermercado.entity.Compra;
import ifpb.api_caixa_supermercado.entity.Produto;

import java.util.List;

public class CompraMapper {

    public static CompraResponseDTO toCompraResponseDTO(Compra compra) {
        List<ProdutoResponseDTO> produtosDTO = compra.getProdutosCompra().stream()
                .map(ProdutoMapper::toProdutoResponseDTO)
                .toList();
        return new CompraResponseDTO(
                compra.getId(),
                compra.getDataCompra(),
                produtosDTO,
                compra.getValorCompra(),
                compra.getFormaPagamento()
        );
    }
}
