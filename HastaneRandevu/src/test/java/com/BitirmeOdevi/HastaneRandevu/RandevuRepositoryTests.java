package com.BitirmeOdevi.HastaneRandevu;

import com.BitirmeOdevi.HastaneRandevu.Entity.Randevu;
import com.BitirmeOdevi.HastaneRandevu.Repository.RandevuRepository;
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
public class RandevuRepositoryTests {

    @Autowired
    private RandevuRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateRandevu() {

    Randevu randevu=new Randevu();
    randevu.setEmail("deniz@gmail.com");
    randevu.setDate("2021-04-12");
    randevu.setTime("12.00");
    randevu.setDescription("Yüksek Ateş");


    Randevu savedRandevu=repo.save(randevu);
    Randevu existRandevu=entityManager.find(Randevu.class, savedRandevu.getId());

        assertThat(existRandevu.getEmail()).isEqualTo(randevu.getEmail());


}}