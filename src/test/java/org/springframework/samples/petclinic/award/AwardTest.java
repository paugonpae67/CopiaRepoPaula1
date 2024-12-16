package org.springframework.samples.petclinic.award;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.surgery.award.AwardAlgorithm;
import org.springframework.samples.petclinic.surgery.award.ValidAwardAlgorithm;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.visit.Visit;

public class AwardTest {
    // This is your SUT:
    AwardAlgorithm algorithm=new ValidAwardAlgorithm();
    
    // Please specify as many tests as you need using structures similar to this:
    /* 
    @Test
    public void someTest(){
        // Do something
        // ...

        // Run the subject under test
        Set<Vet> vets = algorithm.selectAwardedVets(testVisits);

        // Asssert something
        // ...
    }        
    */
   
    // We provide these methods so that you can use them in your tests to 
    // make the creation of valid Visits easier
    public Visit createVisit(Vet vet){
        Visit v=new Visit();
        v.setDatetime(LocalDateTime.now());
        v.setPet(createValidPet());
        v.setVet(vet);
        return v;
    }

    public Vet createVet(String first, String last) {
        Vet v = new Vet();
        v.setFirstName(first);
        v.setLastName(last);
        return v;
    }

    public Pet createValidPet(){
        PetType pt=new PetType();
        pt.setName("Turtle");
        Owner splinter=new Owner();
        splinter.setFirstName("Splinter");
        splinter.setLastName("Master");
        Pet p=new Pet();
        p.setType(pt);
        p.setBirthDate(LocalDate.now());
        p.setOwner(splinter);
        p.setName(List.of("Leo","Donnie","Raph","Mickie").get((int)(Math.random()*4)));        
        return p;
    }

    // This method should not be modified
    public void setAlgorithm(AwardAlgorithm value){
        this.algorithm=value;
    }


    @Test
    public void listaVaciaTest(){
        Set<Visit> lista= new HashSet<>();
        Set<Vet> vets = algorithm.selectAwardedVets(lista);
        assertEquals(Set.of(), vets);
    }


    @Test
    public void lista1VetTest(){
        Vet vet1= createVet("Manolo", "Garcia");
        Visit visita1= createVisit(vet1);
        Set<Visit> lista=Set.of(visita1);
        Set<Vet> vets = algorithm.selectAwardedVets(lista);
        assertEquals(Set.of(vet1), vets);
        assertEquals(1, vets.size());
    }

    @Test
    public void listaVariosVetTest(){
        Vet vet1= createVet("Manolo", "Garcia");
        Vet vet2= createVet("Carlos", "M");
        Vet vet3= createVet("Lucia", "D");
        Visit visita1= createVisit(vet1);
        Visit visita2= createVisit(vet1);
        Visit visita3= createVisit(vet2);
        Visit visita4= createVisit(vet2);
        Visit visita5= createVisit(vet3);

        Set<Visit> lista=Set.of(visita1, visita2, visita3, visita4, visita5);
        Set<Vet> vets = algorithm.selectAwardedVets(lista);
        assertEquals(Set.of(vet1, vet2), vets);
        assertEquals(2, vets.size());
    }

}
