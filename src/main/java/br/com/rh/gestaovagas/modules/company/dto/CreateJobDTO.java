package br.com.rh.gestaovagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateJobDTO(

        @Schema(example = "Vaga para pessoa desenvolvedora júnior", requiredMode = Schema.RequiredMode.REQUIRED)
        String description,

        @Schema(example = "GYMPass, Plano de saúde", requiredMode = Schema.RequiredMode.REQUIRED)
        String benefits,

        @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
        String level
) {}
