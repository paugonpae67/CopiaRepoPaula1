package org.springframework.samples.petclinic.surgery;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SurgeryTypeRepository extends CrudRepository<SurgeryType, Integer>{
        List<SurgeryType> findAll();
}
