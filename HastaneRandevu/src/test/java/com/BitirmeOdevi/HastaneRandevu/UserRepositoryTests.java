package com.BitirmeOdevi.HastaneRandevu;

import com.BitirmeOdevi.HastaneRandevu.Entity.Role;
import com.BitirmeOdevi.HastaneRandevu.Entity.User;
import com.BitirmeOdevi.HastaneRandevu.Repository.RoleRepository;
import com.BitirmeOdevi.HastaneRandevu.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user=new User();
        user.setEmail("alii@gmail.com");
        user.setPassword("1234567");
        user.setFirsName("ali");
        user.setLastName("can");
        user.setBirthday("1998-05-04");
        user.setGender("Erkek");


        User savedUser=userRepo.save(user);
        User existUser=entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    public void testFindUserByEmail(){
        String email = "sari@gmail.com";
        User user=userRepo.getUserByEmail(email);
        assertThat(user).isNotNull();
    }

    @Test
    public void testAddRoleToNewUser(){

        User user=new User();
        user.setEmail("mel@gmail.com");
        user.setPassword("123456");
        user.setFirsName("melike");
        user.setLastName("sarı");
        user.setBirthday("1998-05-04");
        user.setGender("Kadın");

        Role roleUser=roleRepo.findByName("User");
        user.addRole(roleUser);

        User savedUser=userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);

    }

    @Test
    public void testAddRolesToExistingUser(){
        User user=userRepo.findById(1L).get();

        Role roleUser=roleRepo.findByName("User");
        user.addRole(roleUser);

        Role roleAdmin=new Role(2L);
        user.addRole(roleAdmin);

        User savedUser=userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);

    }
}
