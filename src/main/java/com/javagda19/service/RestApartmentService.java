package com.javagda19.service;


import com.javagda19.mapper.ApartmentMapper;
import com.javagda19.model.Apartment;
import com.javagda19.model.Tenant;
import com.javagda19.model.dto.ApartmentDto;
import com.javagda19.model.dto.ApartmentUpdateRequest;
import com.javagda19.model.dto.CreateApartmentDto;
import com.javagda19.repository.ApartmentRepository;
import com.javagda19.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.javagda19.util.ModelUtility.copyNonNullProperties;

@Service
public class RestApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private ApartmentMapper apartmentMapper;

    public void createDefault(CreateApartmentDto apartmentDto) {
        // mapowanie (MapStruct - za chwilę)
        Apartment apartment = new Apartment();
        apartment.setAddress(apartmentDto.getAddress());
        apartment.setZipcode(apartmentDto.getZipcode());
        apartment.setCity(apartmentDto.getCity());

        apartmentRepository.save(apartment);
    }

    public List<ApartmentDto> getAll() {
        return apartmentRepository.findAll()
//                .stream().map(apartmentMapper::apartmentToApartmentDto)
                .stream()
                .map(apartment -> apartmentMapper.apartmentToApartmentDto(apartment))
                .collect(Collectors.toList());
    }

    public ApartmentDto getById(Long id) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(id);

        if (!apartmentOptional.isPresent()) {
            throw new EntityNotFoundException();
        }
        return apartmentMapper.apartmentToApartmentDto(apartmentOptional.get());
    }

    public void removeById(Long id) {
        apartmentRepository.deleteById(id);
    }

    public ApartmentDto update(Long id, ApartmentUpdateRequest updateRequest) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(id);
        if (!apartmentOptional.isPresent()) {
            throw new EntityNotFoundException();
        }

        Apartment apartment = apartmentOptional.get();
        copyNonNullProperties(updateRequest, apartment);

        apartmentRepository.save(apartment); // zapisz zmiany do bazy

        return apartmentMapper.apartmentToApartmentDto(apartment);
    }

    @Transactional
    public void addTenant(Long apartmentId, Long tenantId) {
        Tenant tenant = tenantRepository.getOne(tenantId);

        Optional<Apartment> optionalApartment = apartmentRepository.findById(apartmentId);
        if (!optionalApartment.isPresent()) {
            throw new EntityNotFoundException("Apartment does not exist.");
        }

        Apartment apartment = optionalApartment.get();
        apartment.getTenants().add(tenant);

        apartmentRepository.save(apartment);
    }

    @Transactional
    public ApartmentDto getApartmentWithTenants(Long apartmentId){
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(apartmentId);
        if (!apartmentOptional.isPresent()) {
            throw new EntityNotFoundException();
        }

        return apartmentMapper.apartmentToApartmentDto(apartmentOptional.get());
    }
}
