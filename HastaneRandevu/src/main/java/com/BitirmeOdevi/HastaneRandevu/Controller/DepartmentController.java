package com.BitirmeOdevi.HastaneRandevu.Controller;

import com.BitirmeOdevi.HastaneRandevu.Entity.Department;
import com.BitirmeOdevi.HastaneRandevu.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentRepository repo;

    @GetMapping("/departments")

    public String listDepartment(Model model){
        List<Department> listDepartment=repo.findAll();
        model.addAttribute("listDepartment",listDepartment);
        return "departments";
    }
    @GetMapping("/departments/new")
    public String showDepartmentNewForm(Model model){
        model.addAttribute("department", new Department());
        return "departments_form";
    }

    @PostMapping("/departments/save")
    public String saveDepartment(Department department){
        repo.save(department);
        return "redirect:/departments";
    }
    @GetMapping("/departments/edit/{id}")
    public String showEditDoctorForm(@PathVariable("id") Integer id, Model model){
        Department department=repo.findById(id).get();
        model.addAttribute("department",department);


        return "departments_form";
    }

    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id, Model model) {
        Department department=repo.findById(id).get();
        repo.deleteById(id);

        return "redirect:/departments";
    }
}
