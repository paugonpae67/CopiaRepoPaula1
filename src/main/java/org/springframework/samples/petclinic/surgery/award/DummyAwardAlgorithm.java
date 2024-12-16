package org.springframework.samples.petclinic.surgery.award;

import java.util.Set;

import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.visit.Visit;

public class DummyAwardAlgorithm implements AwardAlgorithm {

    @Override
    public Set<Vet> selectAwardedVets(Set<Visit> visits) {
        return Set.of();
    }
    
}
