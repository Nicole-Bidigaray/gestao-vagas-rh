package br.com.rh.gestaovagas.modules.company.dto;

import java.util.UUID;

public record JobResponseDTO(
        UUID id,
        String description,
        String benefits,
        String level,
        UUID companyId
) {}
