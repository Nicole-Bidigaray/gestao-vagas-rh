package br.com.rh.gestaovagas.modules.company.repositories;

import br.com.rh.gestaovagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    @Query("""
    SELECT j
    FROM JobEntity j
    JOIN FETCH j.company
    WHERE LOWER(j.description) LIKE LOWER(CONCAT('%', :filter, '%'))
  """)
    List<JobEntity> findByDescriptionWithCompany(@Param("filter") String filter);
}
