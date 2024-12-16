package org.springframework.samples.petclinic.disease;

import java.util.List;

public interface TreatmentRepository {

    List<Treatment> findAll();

    Treatment save(Treatment any);
    
}
