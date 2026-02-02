package br.com.rh.gestaovagas.modules.candidate.dto;

public record AuthCandidateResponseDTO(
        String access_token,
        Long expires_in
) {
}
