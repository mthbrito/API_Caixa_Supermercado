package ifpb.api_caixa_supermercado.dto;

import java.time.LocalDateTime;

public record ErroResponseDTO(
        LocalDateTime horario,
        int status,
        String erro,
        java.util.List<String> mensagem
        ) {
}
