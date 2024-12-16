package org.springframework.samples.petclinic.disease;

import java.util.List;

public class TreatmentService {
    private TreatmentRepository repo;

    public TreatmentService(TreatmentRepository tr){
        this.repo=tr;
    }

    public List<Treatment> getAll() {
        return null;
    }

    public Treatment save(Treatment t) {
        return null;
    }
}
