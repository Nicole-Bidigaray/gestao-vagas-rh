package br.com.rh.gestaovagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record ProfileCandidateResponseDTO(

    @Schema(example = "Desenvolvedora Java")
    String description,

    @Schema(example = "maria")
    String username,

    @Schema(example = "maria@gmail.com")
    String email,
    UUID id,

    @Schema(example = "Maria de Souza")
    String name
) {
}
