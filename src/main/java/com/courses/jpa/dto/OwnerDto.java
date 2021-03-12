package com.courses.jpa.dto;

import com.courses.jpa.entity.Owner;
import lombok.Data;

@Data
public class OwnerDto {

    private Long id;
    private String name;

    public OwnerDto() {
    }

    public OwnerDto(String name) {
        this.name = name;
    }

    public static OwnerDto from(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        return ownerDto;
    }
}
