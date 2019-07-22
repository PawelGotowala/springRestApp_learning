package com.javagda19.mapper;

import com.javagda19.model.Apartment;
import com.javagda19.model.dto.ApartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { TenantMapper.class})
public interface ApartmentMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "zipcode", source = "zipcode"),
            @Mapping(target = "city", source = "city"),
            @Mapping(target = "available", source = "available"),
            @Mapping(target = "rooms", source = "rooms"),
            @Mapping(target = "size", source = "size"),
    })
    Apartment apartmentDtoToApartment(ApartmentDto dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "zipcode", target = "zipcode"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "available", target = "available"),
            @Mapping(source = "rooms", target = "rooms"),
            @Mapping(source = "size", target = "size"),
            @Mapping(source = "tenants", target = "tenants")
    })
    ApartmentDto apartmentToApartmentDto(Apartment ap);


}
