package org.springframework.samples.petclinic.surgery;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.vet.Vet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Surgery extends NamedEntity{

    String description;

    @NotNull
    @Column(name= "surgery_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate date;
    
    @NotNull
    @ManyToMany
    Set<Vet> surgeryTeam;  
      
    @NotNull
    @ManyToOne
    OperatingRoom room;
}
