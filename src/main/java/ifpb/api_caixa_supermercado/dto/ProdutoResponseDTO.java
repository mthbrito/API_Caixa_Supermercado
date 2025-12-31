package ifpb.api_caixa_supermercado.dto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Integer id,
        String nome,
        BigDecimal preco,
        String unidade
) {
}
