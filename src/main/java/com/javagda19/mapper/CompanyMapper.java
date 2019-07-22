package com.javagda19.mapper;

import com.javagda19.model.Company;
import com.javagda19.model.dto.CompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "address", source = "address"),
    })
    Company companyDtoToCompany(CompanyDto companyDto);


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "address", target = "address"),
    })
    CompanyDto companyToCompanyDto(Company company);

}
