package org.springframework.samples.petclinic.surgery;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.pet.PetType;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OperatingRoom extends NamedEntity{
    String description;

    @NotNull
    @ManyToMany
    Set<PetType> validFor;    
}
