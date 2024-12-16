package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.surgery.OperatingRoom;
import org.springframework.samples.petclinic.surgery.OperatingRoomRepository;
import org.springframework.samples.petclinic.surgery.Surgery;
import org.springframework.samples.petclinic.surgery.SurgeryRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test1 extends ReflexiveTest{

    @Autowired(required = false)
    SurgeryRepository surgeryRepo;
    @Autowired(required = false)
    OperatingRoomRepository roomRepo;
    
    @Autowired
    EntityManager em;

    @Test
    public void test1RepositoriesExist(){
        assertNotNull(surgeryRepo,"The surgery repository was not injected into the tests, its autowired value was null");
        assertNotNull(roomRepo,"The operation room repository was not injected into the tests, its autowired value was null");
        test1RepositoriesContainsMethod();
    }

    public void test1RepositoriesContainsMethod(){
        if(surgeryRepo!=null){
            Object v=surgeryRepo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a surgery that does not exist");
        }else
            fail("The surgery repository was not injected into the tests, its autowired value was null");
        
        if(roomRepo!=null){
            Object v=roomRepo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a room that does not exist");
        }else
            fail("The room repository was not injected into the tests, its autowired value was null");
    }
    
    

    
    @Test
    public void test1CheckSurgeryConstraints() {
        Map<String,List<Object>> invalidValues=Map.of(
                                            "name",     List.of(
                                                    "      ","a",
                                                    "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. ")
                                            );


        Surgery t=createValidSurgery(em);
        em.persist(t);
        
        checkThatFieldsAreMandatory(t, em, "name","date");        
        
        checkThatValuesAreNotValid(t, invalidValues,em);   
    }
    @Test
    public void test1CheckRoomContraints() {
         Map<String,List<Object>> invalidValues=Map.of(
                                            "name",     List.of(
                                                    "      ","a",
                                                    "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. ")                                            
                                            );


        OperatingRoom s=createValidRoom(em);
        em.persist(s);
        
        checkThatFieldsAreMandatory(s, em, "name");        
        
        checkThatValuesAreNotValid(s, invalidValues,em);   
    }

    
    @Test
    public void test1CheckSurgeryAnnotations() {        
        assertTrue(classIsAnnotatedWith(Surgery.class,Entity.class));
        checkThatFieldIsAnnotatedWith(Surgery.class, "date", Column.class);
        checkThatFieldIsAnnotatedWithDateTimeFormat(Surgery.class,"date","dd/MM/yyyy");        
    }

    @Test
    public void test1CheckOperatingRoomAnnotations() {
        assertTrue(classIsAnnotatedWith(OperatingRoom.class,Entity.class));
    }

    public static OperatingRoom createValidRoom(EntityManager em){        
        OperatingRoom o = new OperatingRoom();
        setValue(o,"name",String.class,"Una habitación propia");
        if (em != null) {
            o.setValidFor(Set.of(em.find(PetType.class,1)));
            em.persist(o);
        } else {
            PetType pt = new PetType();
            pt.setName("gato");
            o.setValidFor(Set.of(pt));
        }
        return o;
    }

    public static Surgery createValidSurgery(EntityManager em){
        Surgery t=new Surgery();
        setValue(t,"name",String.class,"Una cirugía válida");
        t.setDate(LocalDate.of(2023, 12, 16));
        t.setRoom(createValidRoom(em));
        return t;
    }
}
