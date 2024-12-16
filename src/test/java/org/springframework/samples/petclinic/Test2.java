package org.springframework.samples.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.surgery.OperatingRoom;
import org.springframework.samples.petclinic.surgery.Surgery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@DataJpaTest()
public class Test2 extends ReflexiveTest{
    
    @Autowired(required = false)
    EntityManager em;         

    @Test
    public void test2RoomAnnotations() {
        checkThatFieldIsAnnotatedWith(OperatingRoom.class, "validFor", ManyToMany.class);                          
    }

    @Test
    public void test2SurgeryAnnotations() {        
        checkThatFieldIsAnnotatedWith(Surgery.class, "surgeryTeam", ManyToMany.class);        
        checkThatFieldIsAnnotatedWith(Surgery.class, "room", ManyToOne.class);                
    }

    @Test
    public void test2PetAnnotationsAndConstraints(){
        checkThatFieldIsAnnotatedWith(Pet.class, "surgeries", OneToMany.class);
    }

    @Test
    private void test2RoomConstraints() {
        OperatingRoom t=Test1.createValidRoom(em);
        // checkThatFieldsAreMandatory(t, em,"validFor");        
    }

    @Test
    public void test2SurgeryConstraints() {
        Surgery s=Test1.createValidSurgery(em);
        checkThatFieldsAreMandatory(s, em,"room");                
    }


}
