package de.ghostnet.ghostnet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GhostNetMapDto {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String estimatedSize;
    private String status;
}
