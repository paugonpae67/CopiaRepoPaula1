package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.surgery.OperatingRoom;
import org.springframework.samples.petclinic.surgery.OperatingRoomRepository;
import org.springframework.samples.petclinic.surgery.OperatingRoomService;
import org.springframework.samples.petclinic.surgery.Surgery;
import org.springframework.samples.petclinic.surgery.SurgeryRepository;
import org.springframework.samples.petclinic.surgery.SurgeryService;

@ExtendWith(MockitoExtension.class)
public class Test5 extends ReflexiveTest{
    @Mock
    OperatingRoomRepository tr;
    @Mock
    SurgeryRepository sr;

    
    OperatingRoomService ts;    
    SurgeryService ss;
    
    @BeforeEach
    public void configuation(){
        ts=new OperatingRoomService(tr);
        ss=new SurgeryService(sr);
    }
    
    @Test
    public void test5CheckTransactionalityOfOperatingRoomService(){
        checkTransactional(OperatingRoomService.class,"save", OperatingRoom.class);        
        checkTransactional(OperatingRoomService.class,"getAll");
    }
    
    @Test
    public void test5CheckTransactionalityOfSurgeryService(){
        checkTransactional(SurgeryService.class,"save", Surgery.class);        
        checkTransactional(SurgeryService.class,"getAll");
    }    
    
    @Test
    public void test5OperatingRoomServiceCanGetOperatingRooms(){
        assertNotNull(ts,"OperatingRoomService was not injected by spring");
        when(tr.findAll()).thenReturn(List.of());
        List<OperatingRoom> offers=ts.getAll();
        assertNotNull(offers,"The list of OperatingRooms found by the OperatingRoomService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(tr).findAll();            
    }
    

    @Test
    public void test5SurgeryServiceCanGetSurgerys(){
        assertNotNull(ss,"SurgeryService was not injected by spring");
        when(sr.findAll()).thenReturn(List.of());
        List<Surgery> discounts=ss.getAll();
        assertNotNull(discounts,"The list of surgeries found by the SurgeryService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(sr).findAll();               
    }

    @Test
    public void test5SurgeryServiceCanSaveSurgerys(){
        assertNotNull(ss,"SurgeryService was not injected by spring");
        when(sr.save(any(Surgery.class))).thenReturn(null);
        Surgery s= Test1.createValidSurgery(null);
        ss.save(s);        
        // The test fails if the service does not invoke the save function of the repository:
        verify(sr).save(s);     
    }        

    @Test
    public void test5OperatingRoomServiceCanSaveOperatingRooms() {        
        assertNotNull(ts,"OperatingRoomService was not injected by spring");
        when(tr.save(any(OperatingRoom.class))).thenReturn(null);
        OperatingRoom t=Test1.createValidRoom(null);
        ts.save(t);        
        // The test fails if the service does not invoke the save function of the repository:
        verify(tr).save(t);                
    }

}
