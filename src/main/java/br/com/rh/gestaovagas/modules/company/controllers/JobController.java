package br.com.rh.gestaovagas.modules.company.controllers;

import br.com.rh.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.rh.gestaovagas.modules.company.entities.JobEntity;
import br.com.rh.gestaovagas.modules.company.usecases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping
    public ResponseEntity<JobEntity> create(@Valid @RequestBody CreateJobDTO dto) {

        JobEntity job = createJobUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(job);
    }
}
