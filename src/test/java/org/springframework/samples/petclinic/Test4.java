package org.springframework.samples.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.surgery.OperatingRoom;
import org.springframework.samples.petclinic.surgery.Surgery;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test4 extends ReflexiveTest {
    
    @Autowired
    EntityManager em;
    
    @Test
    public void test4OperatingRoomLinks() {        
        checkContainsById(OperatingRoom.class,1,"getValidFor",1,em);
        checkContainsById(OperatingRoom.class,1,"getValidFor",2,em);
        checkContainsById(OperatingRoom.class,2,"getValidFor",3,em);        
        checkContainsById(OperatingRoom.class,2,"getValidFor",4,em);        
        checkContainsById(OperatingRoom.class,2,"getValidFor",7,em);        
    }

    @Test
    public void test4SurgeryLinks() {        
        checkContainsById(Surgery.class,1,"getSurgeryTeam",2,em);
        checkContainsById(Surgery.class,1,"getSurgeryTeam",4,em);
        checkContainsById(Surgery.class,2,"getSurgeryTeam",1,em);
        checkContainsById(Surgery.class,2,"getSurgeryTeam",5,em);
    }

    @Test
    public void test4PetsLinks() {
                         
        checkContainsById(Pet.class,3,"getSurgeries",1,em);
        checkContainsById(Pet.class,1,"getSurgeries",2,em);
        
    }
    
}
