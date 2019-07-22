package com.javagda19.mapper;

import com.javagda19.model.Tenant;
import com.javagda19.model.dto.TenantDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "surname", source = "surname"),
            @Mapping(target = "agreementStartDate", source = "agreementStartDate"),
            @Mapping(target = "agreementEndDate", source = "agreementEndDate"),
            @Mapping(target = "deposited", source = "deposited"),
            @Mapping(target = "documentId", source = "documentId"),
            @Mapping(target = "rent", source = "rent"),
            @Mapping(target = "paymentDayOfMonth", source = "paymentDayOfMonth"),
    })
    Tenant tenantDtoToTenant(TenantDto dto);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "agreementStartDate", target = "agreementStartDate"),
            @Mapping(source = "agreementEndDate", target = "agreementEndDate"),
            @Mapping(source = "deposited", target = "deposited"),
            @Mapping(source = "documentId", target = "documentId"),
            @Mapping(source = "rent", target = "rent"),
            @Mapping(source = "paymentDayOfMonth", target = "paymentDayOfMonth"),
    })
    TenantDto tenantToTenantDto(Tenant ap);

    @IterableMapping(qualifiedByName = {"toDtos"})
    List<TenantDto> tenantListToTenantDtoList(List<Tenant> tenants);
}
