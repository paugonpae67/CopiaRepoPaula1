package org.springframework.samples.petclinic.surgery;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.pet.PetType;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SurgeryType extends NamedEntity {
    @ManyToMany
    Set<PetType> susceptiblePetTypes;
}
