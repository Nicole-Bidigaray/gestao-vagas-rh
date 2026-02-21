package br.com.rh.gestaovagas.modules.company.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [username] é obrigatório")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    private String username;

    @NotBlank(message = "O campo [email] é obrigatório")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @NotBlank(message = "O campo [password] é obrigatório")
    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String website;

    @NotBlank(message = "O campo [name] é obrigatório")
    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

