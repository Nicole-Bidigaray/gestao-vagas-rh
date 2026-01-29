package br.com.rh.gestaovagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateJobDTO(
        String description,
        String benefits,

        @NotBlank(message = "O campo [level] é obrigatório")
        String level,

        @NotNull(message = "O campo [companyId] é obrigatório")
        UUID companyId
) {}
