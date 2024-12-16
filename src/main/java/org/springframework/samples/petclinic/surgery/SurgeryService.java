package org.springframework.samples.petclinic.surgery;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class SurgeryService {
    SurgeryRepository repo;

    public SurgeryService(SurgeryRepository sr){
        this.repo=sr;
    }

    @Transactional(readOnly=true)
    public List<Surgery> getAll() {
        return repo.findAll();
    }

    @Transactional
    public Surgery save(Surgery s) {
        return repo.save(s);
    }
}
