package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users_list";
    }

    @GetMapping("/{id}")
    public String add(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getUser(id));
        return "users_detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "users_add";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getUser(id));
        return "users_add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute User user) {
        // zašifrovat heslo jen pokud je nově zadané nebo při novém uživateli
        if (user.getId() == 0 || !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.saveUser(user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getUser(id));
        return "users_delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }
}