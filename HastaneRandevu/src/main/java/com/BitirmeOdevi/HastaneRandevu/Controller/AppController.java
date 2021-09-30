package com.BitirmeOdevi.HastaneRandevu.Controller;
import com.BitirmeOdevi.HastaneRandevu.Entity.Randevu;
import com.BitirmeOdevi.HastaneRandevu.Entity.Role;
import com.BitirmeOdevi.HastaneRandevu.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.BitirmeOdevi.HastaneRandevu.Entity.User;
import com.BitirmeOdevi.HastaneRandevu.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }
    @GetMapping("/Anasayfa")
    public String Page(){
        return "Anasayfa";
    }

    @GetMapping("/iletisim")
    public String Iletisim(){
        return "iletisim";
    }

    @GetMapping("/hekim")
    public String doctorPage(){
        return "doctor";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/doctorpanel")
    public String doctorpanel(){
        return "doctorpanel";
    }

    @GetMapping("/register")
    public  String showSingUpForm(Model model){
        model.addAttribute("user",new User());

       List <String> genderList= Arrays.asList("Erkek","Kadın");
       model.addAttribute("genderList",genderList);
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processResistration(User user){

        service.saveUserWithDefaultRole(user);
        return"register_success";
    }

    @GetMapping("/list_users")
    public  String viewUsersList(Model model){
        List <User> listUsers=service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id,Model model ){
        User user=service.get(id);
        List<Role> listRoles=service.getRoles();
        model.addAttribute("listRoles",listRoles);
        model.addAttribute("user",user);


        List <String> genderList= Arrays.asList("Erkek","Kadın");
        model.addAttribute("genderList",genderList);

        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);

        return "redirect:/list_users";
    }


}
