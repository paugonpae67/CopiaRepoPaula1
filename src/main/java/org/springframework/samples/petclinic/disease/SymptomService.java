package org.springframework.samples.petclinic.disease;

import java.util.List;

public class SymptomService {
    SymptomRepository repo;

    public SymptomService(SymptomRepository sr){
        this.repo=sr;
    }

    public List<Symptom> getAll() {
        return null;
    }

    public Symptom save(Symptom s) {
        return null;
    }
}
