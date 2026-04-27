package de.ghostnet.ghostnet.service;

import de.ghostnet.ghostnet.entity.GhostNet;
import de.ghostnet.ghostnet.entity.GhostNet.NetStatus;
import de.ghostnet.ghostnet.entity.GhostNetMapDto;
import de.ghostnet.ghostnet.entity.RecoveringPerson;
import de.ghostnet.ghostnet.entity.ReportingPerson;
import de.ghostnet.ghostnet.repository.GhostNetRepository;
import de.ghostnet.ghostnet.repository.RecoveringPersonRepository;
import de.ghostnet.ghostnet.repository.ReportingPersonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GhostNetService {

    private final GhostNetRepository ghostNetRepository;
    private final RecoveringPersonRepository recoveringPersonRepository;
    private final ReportingPersonRepository reportingPersonRepository;
    private static final String ghostNetNotFound = "Geisternetz nicht gefunden";

    // MUST 1: Geisternetz erfassen (anonym oder mit Daten)
    public GhostNet reportGhostNet(GhostNet ghostNet, ReportingPerson reportingPerson) {
        if (!reportingPerson.isAnonymous()) {
            reportingPersonRepository.save(reportingPerson);
            ghostNet.setReportingPerson(reportingPerson);
        }
        ghostNet.setStatus(NetStatus.REPORTED);
        return ghostNetRepository.save(ghostNet);
    }

    // MUST 2: Bergende Person trägt sich ein
    public GhostNet assignRecoveringPerson(Long netId, RecoveringPerson recoveringPerson) {
        GhostNet net = ghostNetRepository.findById(netId)
                .orElseThrow(() -> new RuntimeException(ghostNetNotFound));

        // Immer neue Person anlegen, nie existierende updaten
        RecoveringPerson newPerson = new RecoveringPerson();
        newPerson.setName(recoveringPerson.getName());
        newPerson.setPhoneNumber(recoveringPerson.getPhoneNumber());
        recoveringPersonRepository.save(newPerson);

        net.setRecoveringPerson(newPerson);
        net.setStatus(NetStatus.RECOVERY_PENDING);
        return ghostNetRepository.save(net);
    }

    // MUST 3: Alle noch zu bergenden Netze anzeigen
    public List<GhostNet> getNetsToRecover() {
        return ghostNetRepository.findByStatusNot(NetStatus.RECOVERED);
    }

    // MUST 4: Geisternetz als geborgen markieren
    public GhostNet markAsRecovered(Long netId) {
        GhostNet net = ghostNetRepository.findById(netId)
                .orElseThrow(() -> new RuntimeException(ghostNetNotFound));
        net.setStatus(NetStatus.RECOVERED);
        return ghostNetRepository.save(net);
    }

    // Alle Netze
    public List<GhostNet> getAllNets() {
        return ghostNetRepository.findAll();
    }

    // Ein Netz per ID
    public GhostNet getNetById(Long netId) {
        return ghostNetRepository.findById(netId)
                .orElseThrow(() -> new RuntimeException(ghostNetNotFound));
    }

    // Alle bergenden Personen
    public List<RecoveringPerson> getAllRecoveringPersons() {
        return recoveringPersonRepository.findAll();
    }

    public List<GhostNetMapDto> getNetsForMap() {
        return ghostNetRepository.findByStatusNot(NetStatus.RECOVERED)
                .stream()
                .map(net -> new GhostNetMapDto(
                        net.getId(),
                        net.getLatitude(),
                        net.getLongitude(),
                        net.getEstimatedSize().name(),
                        net.getStatus().name()
                ))
                .toList();
    }
}