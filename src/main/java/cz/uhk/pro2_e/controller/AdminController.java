package cz.uhk.pro2_e.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage() {
        return "admin"; // musíš mít admin.html v templates
    }
}