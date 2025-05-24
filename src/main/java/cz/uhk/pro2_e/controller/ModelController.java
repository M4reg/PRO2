package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.Model;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.security.MyUserDetails;
import cz.uhk.pro2_e.service.ModelService;
import cz.uhk.pro2_e.service.ColorService;
import cz.uhk.pro2_e.service.ChemicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();

        List<Model> models = currentUser.getRole().equals("ADMIN")
                ? modelService.getAllModels()
                : modelService.getAllModelsByUser(currentUser);

        model.addAttribute("models", models);
        return "models_list";
    }

    @GetMapping("/{id}")
    public String detail(org.springframework.ui.Model model, @PathVariable long id) {
        Model m = modelService.getModel(id);
        if (!canAccessModel(m)) return "redirect:/403";
        model.addAttribute("model", m);
        return "models_detail";
    }

    @GetMapping("/add")
    public String add(org.springframework.ui.Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();

        model.addAttribute("model", new Model());

        if (currentUser.getRole().equals("ADMIN")) {
            model.addAttribute("colors", colorService.getAllColors());
            model.addAttribute("chemicals", chemicalService.getAllChemicals());
        } else {
            model.addAttribute("colors", colorService.getAllColorsByUser(currentUser));
            model.addAttribute("chemicals", chemicalService.getAllChemicalsByUser(currentUser));
        }

        return "models_add";
    }

    @GetMapping("/{id}/edit")
    public String edit(org.springframework.ui.Model model, @PathVariable long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();

        Model m = modelService.getModel(id);
        if (!canAccessModel(m)) {
            return "redirect:/403"; // nebo error page
        }

        model.addAttribute("model", m);

        if (currentUser.getRole().equals("ADMIN")) {
            model.addAttribute("colors", colorService.getAllColors());
            model.addAttribute("chemicals", chemicalService.getAllChemicals());
        } else {
            model.addAttribute("colors", colorService.getAllColorsByUser(currentUser));
            model.addAttribute("chemicals", chemicalService.getAllChemicalsByUser(currentUser));
        }

        return "models_add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();

        if (model.getId() == 0) {
            model.setUser(currentUser);
        } else {
            Model original = modelService.getModel(model.getId());
            if (original != null) {
                model.setUser(original.getUser());
            }
        }

        modelService.saveModel(model);
        return "redirect:/models/";
    }


    @GetMapping("/{id}/delete")
    public String delete(org.springframework.ui.Model model, @PathVariable long id) {
        Model m = modelService.getModel(id);
        if (!canAccessModel(m)) {
            return "redirect:/403";
        }

        model.addAttribute("model", m);
        return "models_delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable long id) {
        Model m = modelService.getModel(id);
        if (!canAccessModel(m)) {
            return "redirect:/403";
        }

        modelService.deleteModel(id);
        return "redirect:/models/";
    }

    private boolean canAccessModel(Model m) {
        if (m == null) return false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();
        return currentUser.getRole().equals("ADMIN") || m.getUser().getId() == currentUser.getId();
    }
}