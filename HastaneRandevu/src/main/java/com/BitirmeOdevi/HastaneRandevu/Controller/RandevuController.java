package com.BitirmeOdevi.HastaneRandevu.Controller;


import com.BitirmeOdevi.HastaneRandevu.Entity.Department;
import com.BitirmeOdevi.HastaneRandevu.Entity.Doctor;
import com.BitirmeOdevi.HastaneRandevu.Entity.Randevu;
import com.BitirmeOdevi.HastaneRandevu.Repository.DepartmentRepository;
import com.BitirmeOdevi.HastaneRandevu.Repository.DoctorRepository;
import com.BitirmeOdevi.HastaneRandevu.Repository.RandevuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class RandevuController {

    @Autowired
    private RandevuRepository randevuRepo;
    @Autowired
    private DoctorRepository doctorRepo;
    @Autowired
    private DepartmentRepository departmentRepo;

    @GetMapping("/randevu_create")
    public  String randevuCreate(Model model){
        model.addAttribute("randevu",new Randevu());

        List<Department> listDepartment= departmentRepo.findAll();
        model.addAttribute("listDepartment",listDepartment);

        List<Doctor> listDoctors=doctorRepo.findAll();
        model.addAttribute("listDoctors",listDoctors);

        List<String> timeList= Arrays.asList("09.00","10.00","11.00","12.00","13.00","14.00");
        model.addAttribute("timeList",timeList);

        return "randevu_form";
    }
    @PostMapping("/randevu_create/save")
    public String saveRandevu(@ModelAttribute("randevu") Randevu randevu){
        randevuRepo.save(randevu);
        System.out.println(randevu);
        return "randevu_success";
    }
    @GetMapping("/list_randevu")
    public  String viewRandevuList(Model model) {
        List<Randevu> listRandevu = randevuRepo.findAll();
        model.addAttribute("listRandevu", listRandevu);
        return "randevular";
    }
  /*  @PostMapping("/randevu_create/save")
    public String submitForm(@ModelAttribute("randevu") Randevu randevu){
        System.out.println(randevu);
        return "randevu_success";
    }*/


    @GetMapping("/randevu_create/delete/{id}")
    public String deleteDoctor(@PathVariable("id") Long id, Model model) {
        randevuRepo.deleteById(id);

        return "redirect:/randevu_create";
    }

}
