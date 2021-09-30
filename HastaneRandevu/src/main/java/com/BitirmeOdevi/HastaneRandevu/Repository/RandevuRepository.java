package com.BitirmeOdevi.HastaneRandevu.Repository;

import com.BitirmeOdevi.HastaneRandevu.Entity.Randevu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RandevuRepository extends JpaRepository<Randevu, Long> {

  //  @Query("SELECT do.name, de.department from DOCTOR do join do.department de")
  // public List<DoctorRepository> getJoinInformation();
}
