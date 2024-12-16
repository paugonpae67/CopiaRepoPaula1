package org.springframework.samples.petclinic.surgery;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SurgeryTypeService {
    SurgeryTypeRepository repo;

    @Autowired
    public SurgeryTypeService(SurgeryTypeRepository repo) {
        this.repo = repo;    
    }

    @Transactional(readOnly = true)
    public SurgeryType findSurgeryTypeById(int i) {
        Optional<SurgeryType> od=repo.findById(i);
        return od.isPresent()?od.get():null;
    }
    

    @Transactional(readOnly = true)
    public List<SurgeryType> findSurgeryTypes() {
        return repo.findAll();
    }

}
