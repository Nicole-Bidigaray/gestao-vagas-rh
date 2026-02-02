package br.com.rh.gestaovagas.modules.company.controllers;

import br.com.rh.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.rh.gestaovagas.modules.company.dto.JobResponseDTO;
import br.com.rh.gestaovagas.modules.company.entities.JobEntity;
import br.com.rh.gestaovagas.modules.company.usecases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<JobResponseDTO> create(@Valid @RequestBody CreateJobDTO dto,
                                                 HttpServletRequest request) {

        String companyIdStr = (String) request.getAttribute("company_id");
        UUID companyId = UUID.fromString(companyIdStr);

        JobEntity job = createJobUseCase.execute(dto, companyIdStr);

        JobResponseDTO response = new JobResponseDTO(
                job.getId(),
                job.getDescription(),
                job.getBenefits(),
                job.getLevel(),
                companyId // vem do token (confiável)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
