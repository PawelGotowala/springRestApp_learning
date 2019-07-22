package com.javagda19.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentUpdateRequest {

    private String address;
    private String city;
    private String zipcode;
    private Double size;
    private Integer rooms;
    private Boolean available;
}
