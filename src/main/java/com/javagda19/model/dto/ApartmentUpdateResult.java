package com.javagda19.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentUpdateResult {
    private Long id;
    private ApartmentUpdateRequest request;
    private ApartmentDto result;

    public static ApartmentUpdateResult createFrom(Long id, ApartmentUpdateRequest updateRequest, ApartmentDto apartmentDto) {
        return new ApartmentUpdateResult(id, updateRequest, apartmentDto);
    }
}
