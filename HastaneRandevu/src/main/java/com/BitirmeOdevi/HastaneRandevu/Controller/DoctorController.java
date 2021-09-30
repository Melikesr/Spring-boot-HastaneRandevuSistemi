package com.BitirmeOdevi.HastaneRandevu.Controller;

import com.BitirmeOdevi.HastaneRandevu.Entity.Department;
import com.BitirmeOdevi.HastaneRandevu.Entity.Doctor;
import com.BitirmeOdevi.HastaneRandevu.Repository.DepartmentRepository;
import com.BitirmeOdevi.HastaneRandevu.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;





    @GetMapping("/doctors/new")
    public String showDoctorNewForm(Model model){
        List<Department> listDepartment= departmentRepository.findAll();
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("listDepartment",listDepartment);

        List <String> genderList= Arrays.asList("Erkek","Kadın");
        model.addAttribute("genderList",genderList);
        return "doctor_form";
    }

    @PostMapping("/doctors/save")
    public String saveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors")
    public String listDoctors(Model model){
        List<Doctor> listDoctors=doctorRepository.findAll();
        model.addAttribute("listDoctors",listDoctors);

        return "doctors";
    }

    @GetMapping("/doctors/edit/{id}")
    public String showEditDoctorForm(@PathVariable("id") Long id, Model model){
        Doctor doctor=doctorRepository.findById(id).get();
        model.addAttribute("doctor",doctor);

        List<Department> listDepartment= departmentRepository.findAll();
        model.addAttribute("listDepartment",listDepartment);

        List <String> genderList= Arrays.asList("Erkek","Kadın");
        model.addAttribute("genderList",genderList);

        return "doctor_form";
    }
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable("id") Long id, Model model) {
        doctorRepository.deleteById(id);

        return "redirect:/doctors";
    }
}
