package de.ghostnet.ghostnet.controller;

import de.ghostnet.ghostnet.entity.GhostNet;
import de.ghostnet.ghostnet.entity.RecoveringPerson;
import de.ghostnet.ghostnet.entity.ReportingPerson;
import de.ghostnet.ghostnet.service.GhostNetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class GhostNetController {

    private final GhostNetService ghostNetService;
    private static final String redirectRouting = "redirect:/";

    // Startseite – Übersicht aller Netze
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("nets", ghostNetService.getAllNets());
        return "index";
    }

    // MUST 1: Formular zum Erfassen eines Geisternetzes
    @GetMapping("/report")
    public String reportForm(Model model) {
        model.addAttribute("ghostNet", new GhostNet());
        model.addAttribute("reportingPerson", new ReportingPerson());
        model.addAttribute("sizes", GhostNet.NetSize.values());
        return "report";
    }

    @PostMapping("/report")
    public String reportSubmit(
            @ModelAttribute GhostNet ghostNet,
            @ModelAttribute ReportingPerson reportingPerson) {
        ghostNetService.reportGhostNet(ghostNet, reportingPerson);
        return redirectRouting;
    }

    // MUST 2: Formular zum Eintragen als bergende Person
    @GetMapping("/recover/{id}")
    public String recoverForm(@PathVariable Long id, Model model) {
        model.addAttribute("net", ghostNetService.getNetById(id));
        model.addAttribute("recoveringPerson", new RecoveringPerson());
        return "recover";
    }

    @PostMapping("/recover/{id}")
    public String recoverSubmit(
            @PathVariable Long id,
            @ModelAttribute RecoveringPerson recoveringPerson) {
        ghostNetService.assignRecoveringPerson(id, recoveringPerson);
        return redirectRouting;
    }

    // MUST 3: Liste der zu bergenden Netze
    @GetMapping("/nets-to-recover")
    public String netsToRecover(Model model) {
        model.addAttribute("nets", ghostNetService.getNetsToRecover());
        return "nets-to-recover";
    }

    // MUST 4: Netz als geborgen markieren
    @PostMapping("/mark-recovered/{id}")
    public String markRecovered(@PathVariable Long id) {
        ghostNetService.markAsRecovered(id);
        return redirectRouting;
    }
    // COULD 5: Weltkarte
    @GetMapping("/map")
    public String map(Model model) {
        model.addAttribute("nets", ghostNetService.getNetsToRecover());
        return "map";
    }
}