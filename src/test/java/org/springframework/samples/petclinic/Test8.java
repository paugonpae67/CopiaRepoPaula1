package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.surgery.SurgeryType;
import org.springframework.samples.petclinic.surgery.SurgeryTypeService;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetService;
import org.springframework.samples.petclinic.visit.UnfeasibleSurgeryException;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.samples.petclinic.visit.VisitService;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.SystemException;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test8 {

	@MockBean
	VisitRepository vr;

    @Autowired
	VisitService visitService;    

	@Autowired 
	SurgeryTypeService surgeryTypeService;

	@Autowired 
	PetService  petService;

	@Autowired
	VetService vetservice;

	@Autowired
	EntityManager em;    

	@Test
	public void test8UnfeasibleSurgeryRecommended() throws SystemException {
		Visit v=createInvalidSurgeryType();		
		
		assertThrows(UnfeasibleSurgeryException.class, () -> visitService.save(v),"The surgery type is not valid for the pet type. An exception should be thrown.");		
		// assertTrue(em.getTransaction().getRollbackOnly());
	}

	@Test
	public void test8ValidDiagnoseSaved() {
		Visit v=createValidDiagnose();				
		try {
			visitService.save(v);				
		} catch (UnfeasibleSurgeryException e) {
			fail("The surgery type is valid for the pet type. No exception should be thrown. :"+e.getMessage());
		}
		verify(vr,times(1)).save(v);
	}

	private Visit createValidDiagnose() {
		Vet vet=vetservice.findVetById(1);
        Pet p=petService.findPetById(1);
		SurgeryType st = surgeryTypeService.findSurgeryTypeById(2);
		Visit result=new Visit();
		result.setPet(p);
		result.setRecommends(st);
		result.setDatetime(LocalDateTime.now());
		result.setVet(vet);
        return result;
	}

	private Visit createInvalidSurgeryType() {
		Vet vet=vetservice.findVetById(1);
        Pet p=petService.findPetById(3);
		SurgeryType st = surgeryTypeService.findSurgeryTypeById(2);
		Visit result=new Visit();
		result.setPet(p);
		result.setRecommends(st);
		result.setDatetime(LocalDateTime.now());
		result.setVet(vet);
        return result;
	}
}
