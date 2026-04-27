package de.ghostnet.ghostnet.repository;

import de.ghostnet.ghostnet.entity.ReportingPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportingPersonRepository extends CrudRepository<ReportingPerson, Long> {
}
