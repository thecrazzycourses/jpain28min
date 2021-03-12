package com.courses.jpa.service;

import com.courses.jpa.entity.Owner;
import com.courses.jpa.repositories.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner createNewOwner(Owner owner) {

        // Create Relationship : Owner is owning side and need car to be persisted first
        owner.getCar().setOwner(owner);
        owner.setCar(owner.getCar());

        // Save Owner to DB (Owner is having cascade All which will save car as well)
        Owner saveOwner = ownerRepository.save(owner);

        return saveOwner;
    }

    public Owner findById(Long id) {
        Optional<Owner> byId = ownerRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    public List<Owner> findAll() {
        List<Owner> all = ownerRepository.findAll();
        return all;
    }
}
