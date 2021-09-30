package com.BitirmeOdevi.HastaneRandevu;

import com.BitirmeOdevi.HastaneRandevu.Entity.Department;
import com.BitirmeOdevi.HastaneRandevu.Repository.DepartmentRepository;
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
public class DeprtmentRepositoryTest {

    @Autowired
    private DepartmentRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateDepartment(){
        Department saveDepartment=repo.save(new Department("göz hastalıkları"));


        assertThat(saveDepartment.getId()).isGreaterThan(0);


    }
}
