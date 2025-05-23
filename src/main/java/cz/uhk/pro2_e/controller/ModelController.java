package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.Model;
import cz.uhk.pro2_e.service.ModelService;
import cz.uhk.pro2_e.service.ColorService;
import cz.uhk.pro2_e.service.ChemicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;
    private final ColorService colorService;
    private final ChemicalService chemicalService;

    @Autowired
    public ModelController(ModelService modelService, ColorService colorService, ChemicalService chemicalService) {
        this.modelService = modelService;
        this.colorService = colorService;
        this.chemicalService = chemicalService;
    }

    @GetMapping("/")
    public String list(org.springframework.ui.Model model) {
        model.addAttribute("models", modelService.getAllModels());
        return "models_list";
    }

    @GetMapping("/{id}")
    public String detail(org.springframework.ui.Model model, @PathVariable long id) {
        model.addAttribute("model", modelService.getModel(id));
        return "models_detail";
    }

    @GetMapping("/add")
    public String add(org.springframework.ui.Model model) {
        model.addAttribute("model", new Model());
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("chemicals", chemicalService.getAllChemicals());
        return "models_add";
    }

    @GetMapping("/{id}/edit")
    public String edit(org.springframework.ui.Model model, @PathVariable long id) {
        model.addAttribute("model", modelService.getModel(id));
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("chemicals", chemicalService.getAllChemicals());
        return "models_add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Model model) {
        modelService.saveModel(model);
        return "redirect:/models/";
    }

    @GetMapping("/{id}/delete")
    public String delete(org.springframework.ui.Model model, @PathVariable long id) {
        model.addAttribute("model", modelService.getModel(id));
        return "models_delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable long id) {
        modelService.deleteModel(id);
        return "redirect:/models/";
    }
}