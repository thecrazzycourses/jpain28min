package com.courses.jpa.api;

import com.courses.jpa.entity.Owner;
import com.courses.jpa.dto.OwnerDto;
import com.courses.jpa.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerApi {

    private final OwnerService ownerService;

    public OwnerApi(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<Owner> createNewOwner(@RequestBody Owner owner) {

        // Call Service Layer
        Owner newOwner = ownerService.createNewOwner(owner);

        // Return Response
        return new ResponseEntity<Owner>(newOwner, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerDetailsById(@PathVariable Long id) {
        Owner byId = ownerService.findById(id);

        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(byId.getId());
        ownerDto.setName(byId.getName());

        return new ResponseEntity<OwnerDto>(ownerDto, HttpStatus.OK);
    }

    @GetMapping
    public List<OwnerDto> findAll() {

        List<Owner> all = ownerService.findAll();
        List<OwnerDto> dtoList = new ArrayList<>();

        for(Owner owner: all) {
            OwnerDto ownerDto = new OwnerDto();

            ownerDto.setId(owner.getId());
            ownerDto.setName(owner.getName());

            dtoList.add(ownerDto);
        }

        return dtoList;
    }
}
