package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.Color;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.security.MyUserDetails;
import cz.uhk.pro2_e.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/colors")
public class ColorController {

    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/")
    public String list(org.springframework.ui.Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();

        List<Color> colors = currentUser.getRole().equals("ADMIN")
                ? colorService.getAllColors()
                : colorService.getAllColorsByUser(currentUser);

        model.addAttribute("colors", colors);
        return "colors_list";
    }


    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("color", colorService.getColor(id));
        return "colors_detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("color", new Color());
        return "colors_add";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("color", colorService.getColor(id));
        return "colors_add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Color color) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        User currentUser = userDetails.getUser();

        if (color.getId() == 0) {
            color.setUser(currentUser);
        }

        colorService.saveColor(color);
        return "redirect:/colors/";
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable long id) {
        model.addAttribute("color", colorService.getColor(id));
        return "colors_delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable long id) {
        colorService.deleteColor(id);
        return "redirect:/colors/";
    }
}