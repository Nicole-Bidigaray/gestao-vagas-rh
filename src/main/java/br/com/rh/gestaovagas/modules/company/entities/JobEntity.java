package br.com.rh.gestaovagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "jobs")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    private String benefits;

    @NotBlank(message = "O campo [level] é obrigatório")
    private String level;

    // ✅ Relacionamento com Company
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    // ✅ Campo auxiliar: pega só o UUID sem carregar a Company inteira
    @Column(name = "company_id", insertable = false, updatable = false)
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
