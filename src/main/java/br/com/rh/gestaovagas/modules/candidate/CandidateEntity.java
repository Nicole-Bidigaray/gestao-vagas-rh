package br.com.rh.gestaovagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "candidates")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [name] é obrigatório")
    @Schema(example = "Daniel de Souza", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @NotBlank(message = "O campo [username] é obrigatório")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Length(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
    @Schema(example = "daniel", requiredMode = Schema.RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;

    @NotBlank(message = "O campo [email] é obrigatório")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    @Schema(example = "daniel@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "E-mail do candidato")
    private String email;

    @NotBlank(message = "O campo [password] é obrigatório")
    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;

    @Schema(example = "Desevolvedor Java", requiredMode = Schema.RequiredMode.REQUIRED, description = "Breve descrição do candidato")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
