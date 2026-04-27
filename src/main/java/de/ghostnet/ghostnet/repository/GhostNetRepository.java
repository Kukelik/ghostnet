package de.ghostnet.ghostnet.repository;

import de.ghostnet.ghostnet.entity.GhostNet;
import de.ghostnet.ghostnet.entity.GhostNet.NetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {

    List<GhostNet> findByStatus(NetStatus status);
    List<GhostNet> findByStatusNot(NetStatus status);

}
