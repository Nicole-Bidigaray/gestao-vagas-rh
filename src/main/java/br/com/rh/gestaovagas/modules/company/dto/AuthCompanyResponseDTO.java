package br.com.rh.gestaovagas.modules.company.dto;

public record AuthCompanyResponseDTO(
        String access_token,
        Long expires_in
) {
}
