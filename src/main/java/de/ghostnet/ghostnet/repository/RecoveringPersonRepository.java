package de.ghostnet.ghostnet.repository;

import de.ghostnet.ghostnet.entity.RecoveringPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveringPersonRepository extends CrudRepository<RecoveringPerson, Long> {
}
