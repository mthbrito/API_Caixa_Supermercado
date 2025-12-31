package ifpb.api_caixa_supermercado.dto;

import ifpb.api_caixa_supermercado.entity.FormaPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CompraRequestDTO(
        @NotEmpty
        @Valid
        List<ProdutoRequestDTO> produtosCompra,
        @NotNull
        FormaPagamento formaPagamento) {
}