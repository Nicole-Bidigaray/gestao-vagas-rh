package br.com.rh.gestaovagas.modules.company.dto;

public record CreateJobDTO(
        String description,
        String benefits,
        String level
) {}
