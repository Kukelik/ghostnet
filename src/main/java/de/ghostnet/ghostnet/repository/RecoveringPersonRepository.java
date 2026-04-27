package de.ghostnet.ghostnet.repository;

import de.ghostnet.ghostnet.entity.RecoveringPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveringPersonRepository extends JpaRepository<RecoveringPerson, Long> {
}
