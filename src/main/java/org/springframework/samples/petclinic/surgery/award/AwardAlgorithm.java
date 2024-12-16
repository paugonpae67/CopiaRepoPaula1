package org.springframework.samples.petclinic.surgery.award;

import java.util.Set;

import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.visit.Visit;

public interface AwardAlgorithm {
    Set<Vet> selectAwardedVets(Set<Visit> visits);
}
