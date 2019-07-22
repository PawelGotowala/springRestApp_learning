package com.javagda19.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApartmentDto {
    private String address;
    private String zipcode;
    private String city;
}
