package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.Chemical;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.security.MyUserDetails;
import cz.uhk.pro2_e.service.ChemicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chemicals")
public class ChemicalController {

    private final ChemicalService chemicalService;

    @Autowired
    public ChemicalController(ChemicalService chemicalService) {
        this.chemicalService = chemicalService;
    }

    @GetMapping("/")
    public String list(org.springframework.ui.Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();

        List<Chemical> chemicals = currentUser.getRole().equals("ADMIN")
                ? chemicalService.getAllChemicals()
                : chemicalService.getAllChemicalsByUser(currentUser);

        model.addAttribute("chemicals", chemicals);
        return "chemicals_list";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("chemical", chemicalService.getChemical(id));
        return "chemicals_detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("chemical", new Chemical());
        return "chemicals_add";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("chemical", chemicalService.getChemical(id));
        return "chemicals_add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Chemical chemical) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();

        if (chemical.getId() == 0) {
            chemical.setUser(currentUser);
        } else {
            Chemical original = chemicalService.getChemical(chemical.getId());
            if (original != null) {
                chemical.setUser(original.getUser());
            }
        }

        chemicalService.saveChemical(chemical);
        return "redirect:/chemicals/";
    }


    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable long id) {
        model.addAttribute("chemical", chemicalService.getChemical(id));
        return "chemicals_delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable long id) {
        chemicalService.deleteChemical(id);
        return "redirect:/chemicals/";
    }
}