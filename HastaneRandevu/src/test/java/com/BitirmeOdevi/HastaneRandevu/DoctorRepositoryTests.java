package com.BitirmeOdevi.HastaneRandevu;

import com.BitirmeOdevi.HastaneRandevu.Entity.Doctor;
import com.BitirmeOdevi.HastaneRandevu.Repository.DoctorRepository;

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
public class DoctorRepositoryTests {

    @Autowired
    private DoctorRepository repo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public  void testCreatDoctor(){
        Doctor doctor=new Doctor();
        doctor.setEmail("osman@gmail.com");
        doctor.setGender("Erkek");
        doctor.setName("osman");
        doctor.setLastName("çelik");


        Doctor savedUser=repo.save(doctor);
        Doctor existUser=entityManager.find(Doctor.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(doctor.getEmail());

    }
}

