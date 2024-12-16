package org.springframework.samples.petclinic.surgery;

import java.util.List;
import java.util.Optional;

public interface SurgeryRepository {
    Optional<Surgery> findById(Integer i);

    List<Surgery> findAll();

    Surgery save(Surgery any);
    
}
