package org.springframework.samples.petclinic.surgery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/surgerytypes")
public class SurgeryTypeController {


    @Autowired
    SurgeryTypeService surgeryTypeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SurgeryType> getAll(){
        return surgeryTypeService.findSurgeryTypes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SurgeryType getSurgeryType(@PathVariable("id") @Valid Integer id){
        SurgeryType st= surgeryTypeService.findSurgeryTypeById(id);
        if(st==null){
            throw new ResourceNotFoundException("Not found");
        }
        return st;
    }
}
