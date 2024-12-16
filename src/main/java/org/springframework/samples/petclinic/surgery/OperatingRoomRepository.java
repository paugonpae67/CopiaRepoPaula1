package org.springframework.samples.petclinic.surgery;

import java.util.List;
import java.util.Optional;

public interface OperatingRoomRepository {

    Optional<OperatingRoom> findById(Integer i);

    List<OperatingRoom> findAll();

    OperatingRoom save(OperatingRoom any);
    
}
