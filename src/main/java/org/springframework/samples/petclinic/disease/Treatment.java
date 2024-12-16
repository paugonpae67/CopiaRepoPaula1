package org.springframework.samples.petclinic.disease;

import java.util.Set;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Treatment {
    String description;
    Integer dose;
    @Transient
    Set<Disease> recommendedFor;
}
