package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.repository.ChemicalRepository;
import cz.uhk.pro2_e.repository.ColorRepository;
import cz.uhk.pro2_e.repository.ModelRepository;
import cz.uhk.pro2_e.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ChemicalRepository chemicalRepository;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("userCount", userRepository.count());
        model.addAttribute("modelCount", modelRepository.count());
        model.addAttribute("colorCount", colorRepository.count());
        model.addAttribute("chemicalCount", chemicalRepository.count());
        return "admin";
    }

}