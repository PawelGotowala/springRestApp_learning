package com.javagda19.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDto {
    private Long id;
    private String address;
    private String zipcode;
    private String city;
    private Double size;
    private Integer rooms;
    private Boolean available;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<TenantDto> tenants;

}
