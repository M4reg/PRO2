package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.Chemical;
import cz.uhk.pro2_e.service.ChemicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chemicals")
public class ChemicalController {

    private final ChemicalService chemicalService;

    @Autowired
    public ChemicalController(ChemicalService chemicalService) {
        this.chemicalService = chemicalService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("chemicals", chemicalService.getAllChemicals());
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