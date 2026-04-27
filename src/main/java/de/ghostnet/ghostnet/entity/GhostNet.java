package de.ghostnet.ghostnet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="ghost_nets")
public class GhostNet {

    @Id
    @GeneratedValue
    private Long id;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private NetSize estiamtedSize;

    @Enumerated(EnumType.STRING)
    private NetStatus status;

    @ManyToOne
    @JoinColumn(name = "recovering_person_id")
    private RecoveringPerson recoveringPerson;

    public enum NetSize {
        SMALL, MEDIUM, LARGE, VERY_LARGE
    }

    public enum NetStatus {
        REPORTED, RECOVERY_PENDING, RECOVERED, LOST
    }

}
