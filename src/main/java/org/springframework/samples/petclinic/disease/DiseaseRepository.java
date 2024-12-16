package org.springframework.samples.petclinic.disease;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.pet.PetType;

public interface DiseaseRepository extends CrudRepository<Disease, Integer> {
    @Query("SELECT d FROM Disease d")
    Set<Disease> findEpidemicDiseases(@Param("petTypes") Set<PetType> petTypes,
                                        @Param("startDate") LocalDateTime startDate, 
                                        @Param("endDate") LocalDateTime endDate, 
                                        @Param("diagnoses")Integer diagnoses);

    List<Disease> findAll();
}
