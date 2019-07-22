package com.javagda19.service;

import com.javagda19.mapper.TenantMapper;
import com.javagda19.model.Tenant;
import com.javagda19.model.dto.TenantDto;
import com.javagda19.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.javagda19.util.ModelUtility.copyNonNullProperties;

@Service
public class RestTenantService {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private TenantMapper tenantMapper;

    public void create(TenantDto tenantDto) {
        tenantRepository.save(tenantMapper.tenantDtoToTenant(tenantDto));
    }

    public TenantDto getById(Long id) {
        Optional<Tenant> tenantOptional = tenantRepository.findById(id);
        return tenantMapper.tenantToTenantDto(tenantOptional.orElseThrow(EntityNotFoundException::new));
    }

    public void removeById(Long id) {
        tenantRepository.deleteById(id);
    }

    public List<TenantDto> getAll() {
        return tenantRepository.findAll()
                .stream().map(tenantMapper::tenantToTenantDto)
                .collect(Collectors.toList());
    }

    public TenantDto update(Long id, TenantDto updateRequest) {
        Optional<Tenant> tenantOptional = tenantRepository.findById(id);
        if (!tenantOptional.isPresent()) {
            throw new EntityNotFoundException("Tenant with this id does not exist.");
        }

        Tenant tenant = tenantOptional.get();
        copyNonNullProperties(updateRequest, tenant);

        tenantRepository.save(tenant); // zapisz zmiany do bazy

        return tenantMapper.tenantToTenantDto(tenant);
    }
}
