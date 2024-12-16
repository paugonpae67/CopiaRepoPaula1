package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test6 {
       
    @Autowired
    VetRepository dr;
        
    @Autowired
    EntityManager em;
    @Test
    public void test() {
        validatefindByComplexCriteria();
    }    

    private void 
    validatefindByComplexCriteria() {
        PetType cats=em.find(PetType.class, 1);
        PetType dogs=em.find(PetType.class, 2);
        PetType hamsters=em.find(PetType.class, 6);
        Set<Vet> vets = dr.findMoreActiveVets(            
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            Set.of(dogs,hamsters),    
            3
        );
        assertNotNull(vets);
        assertEquals(1, vets.size());
        assertEquals("Carter",vets.iterator().next().getLastName());

        vets = dr.findMoreActiveVets(            
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            Set.of(dogs,hamsters),    
            1
        );
        assertNotNull(vets);
        assertEquals(3, vets.size());
        
        vets = dr.findMoreActiveVets(            
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            Set.of(dogs,hamsters),    
            5
        );
        assertNotNull(vets);
        assertEquals(0, vets.size());

        vets = dr.findMoreActiveVets(            
            LocalDateTime.of(2000,11,12,0,0,0),
            LocalDateTime.of(2000, 12, 11,0,0,0),
            Set.of(dogs,hamsters),    
            1
        );
        assertNotNull(vets);
        assertEquals(0, vets.size());

        vets = dr.findMoreActiveVets(            
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            Set.of(dogs),    
            2
        );
        assertNotNull(vets);
        assertEquals(1, vets.size());
        assertEquals("Carter",vets.iterator().next().getLastName());

        vets = dr.findMoreActiveVets(            
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            Set.of(cats),    
            1
        );
        assertNotNull(vets);
        assertEquals(1, vets.size());
        assertEquals("Ortega",vets.iterator().next().getLastName());        

    }
}
