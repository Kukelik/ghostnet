package de.ghostnet.ghostnet.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name ="recovering_person")
public class RecoveringPerson {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "recoveringPerson")
    private List<GhostNet> assignedNets;
}
