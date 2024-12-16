package org.springframework.samples.petclinic.surgery;

import java.util.Set;

import org.springframework.samples.petclinic.pet.PetType;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatingRoom {
    String description;

    @Transient
    Set<PetType> validFor;    
}
