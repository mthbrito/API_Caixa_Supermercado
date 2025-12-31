package ifpb.api_caixa_supermercado.dto;

import ifpb.api_caixa_supermercado.entity.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CompraResponseDTO (
    Integer id,
    LocalDateTime dataCompra,
    List<ProdutoResponseDTO> produtosCompra,
    BigDecimal valorCompra,
    FormaPagamento formaPagamento) {
}
