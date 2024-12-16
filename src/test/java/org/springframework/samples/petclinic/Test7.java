package org.springframework.samples.petclinic;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Test7 {    	    	
    
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
		.webAppContextSetup(context)
		.apply(SecurityMockMvcConfigurers.springSecurity())
		.build();
	}    	

	@Test
    @Transactional
    @WithMockUser(username = "vet1", authorities = {"VET"})
    public void test7VetsCanGetSurgeryTypes() throws JsonProcessingException, Exception{        
       mockMvc.perform(get("/api/v1/surgerytypes"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)));        
    }

	@Test
    @Transactional
    @WithMockUser(username = "vet1", authorities = {"VET"})
    public void test7VetsCanGettypeThatExist() throws JsonProcessingException, Exception{        
       mockMvc.perform(get("/api/v1/surgerytypes/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is("Dental extractions")));        
    }

	@Test
    @Transactional
    @WithMockUser(username = "vet1", authorities = {"VET"})
    public void test7VetsCanNotGettypesThatDontExist() throws JsonProcessingException, Exception{        
       mockMvc.perform(get("/api/v1/surgerytypes/46231"))
			.andExpect(status().isNotFound());        
    }



	@Test
    @Transactional
    @WithMockUser(username = "owner1", authorities = {"OWNER"})
    public void test7OwnersCannotGettypes() throws JsonProcessingException, Exception{        
       mockMvc.perform(get("/api/v1/surgerytypes"))
			.andExpect(status().isForbidden());	        
    }

	@Test
    @Transactional
    @WithMockUser(username = "owner1", authorities = {"OWNER"})
    public void test7OwnersCannotGetSpecifictypes() throws JsonProcessingException, Exception{        
       mockMvc.perform(get("/api/v1/surgerytypes/1"))
			.andExpect(status().isForbidden());	        
    }
	
}