package br.com.rh.gestaovagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthCompanyDTO(

        @NotBlank(message = "Username é obrigatório")
        String username,

        @NotBlank(message = "Password é obrigatório")
        String password
) {}
