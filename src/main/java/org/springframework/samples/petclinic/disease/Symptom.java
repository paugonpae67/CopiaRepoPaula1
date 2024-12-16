package org.springframework.samples.petclinic.disease;

import java.util.Set;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Symptom {
    String description;
    
    @Transient
    Set<Disease> includes;
    
    @Transient
    Set<Disease> excludes;
}
