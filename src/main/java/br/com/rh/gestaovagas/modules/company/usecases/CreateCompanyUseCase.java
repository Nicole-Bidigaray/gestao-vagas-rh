package br.com.rh.gestaovagas.modules.company.usecases;

import br.com.rh.gestaovagas.exceptions.UserFoundException;
import br.com.rh.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.rh.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(
            CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CompanyEntity execute(CompanyEntity companyEntity) {

        companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(user -> { throw new UserFoundException(); });

        companyEntity.setPassword(passwordEncoder.encode(companyEntity.getPassword()));

        return companyRepository.save(companyEntity);
    }
}
