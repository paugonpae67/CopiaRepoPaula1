package org.springframework.samples.petclinic.disease;

import java.util.List;
import java.util.Optional;

public interface SymptomRepository {

    Optional<Symptom> findById(Integer i);

    List<Symptom> findAll();

    Symptom save(Symptom any);
    
}
