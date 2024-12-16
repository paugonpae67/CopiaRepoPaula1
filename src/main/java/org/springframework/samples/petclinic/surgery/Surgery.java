package org.springframework.samples.petclinic.surgery;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.samples.petclinic.vet.Vet;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Surgery {
    String description;

    LocalDate date;
    
    @Transient
    Set<Vet> surgeryTeam;  
      
    @Transient
    OperatingRoom room;
}
