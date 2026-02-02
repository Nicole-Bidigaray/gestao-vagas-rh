package br.com.rh.gestaovagas.modules.candidate.dto;

import java.util.UUID;

public record ProfileCandidateResponseDTO(
    String description,
    String username,
    String email,
    UUID id,
    String name
) {
}
