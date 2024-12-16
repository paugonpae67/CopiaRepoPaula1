package org.springframework.samples.petclinic.surgery;

import java.util.List;

public class SurgeryService {
    SurgeryRepository repo;

    public SurgeryService(SurgeryRepository sr){
        this.repo=sr;
    }

    public List<Surgery> getAll() {
        return null;
    }

    public Surgery save(Surgery s) {
        return null;
    }
}
