package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.surgery.OperatingRoom;
import org.springframework.samples.petclinic.surgery.Surgery;

import jakarta.persistence.EntityManager;

@DataJpaTest()
public class Test3 extends ReflexiveTest {    
    @Autowired
    EntityManager em;    
    
    @Test
    public void test3InitialSurgery(){                        
        Surgery t1=em.find(Surgery.class,1);
        assertNotNull(t1,"There should exist a Surgery with id:1");
        assertEquals("Dental extraction",getFieldValueReflexively(t1, "name"));
        assertNull(t1.getDescription());
        assertEquals(LocalDate.of(2023, 12, 16),t1.getDate());        
        assertEquals(em.find(OperatingRoom.class, 1), t1.getRoom());

        Surgery t2=em.find(Surgery.class,2); 
        assertNotNull(t2,"There should exist a Surgery with id:2");       
        assertEquals("Bladder surgery",getFieldValueReflexively(t2, "name"));
        assertEquals("Removal of a bladder stone.",t2.getDescription());
        assertEquals(LocalDate.of(2023,12,17),t2.getDate());
        assertEquals(em.find(OperatingRoom.class, 2), t2.getRoom());

    }
    @Test
    public void test3InitialOperatingRoom()
    {
        OperatingRoom s1 = em.find(OperatingRoom.class, 1);
        assertNotNull(s1,"Cannot find operating room with id "+1);
        assertEquals("Sala A",getFieldValueReflexively(s1,"name"));
        assertEquals("Sala de operaciones para perros y gatos",s1.getDescription());
        
        OperatingRoom s2 = em.find(OperatingRoom.class, 2);        
        assertNotNull(s2,"Cannot find operating room with id "+2);
        assertEquals("Sala B",getFieldValueReflexively(s2,"name"));
        assertEquals("Sala de operaciones para reptiles y tortugas",s2.getDescription());        
    }        
    
}
