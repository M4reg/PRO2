package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.Department;
import cz.uhk.pro2_e.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "departments_list";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("department", departmentService.getDepartment(id));
        return "departments_detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("department", new Department());
        return "departments_add";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("department", departmentService.getDepartment(id));
        return "departments_add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments/";
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable long id) {
        model.addAttribute("department", departmentService.getDepartment(id));
        return "departments_delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments/";
    }
}
