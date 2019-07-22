    package com.javagda19.service;

import com.javagda19.model.Apartment;
import com.javagda19.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

    @Service
    public class ApartmentService {

        @Autowired
        private ApartmentRepository apartmentRepository;


        public List<Apartment> getAllApartments() {
            return apartmentRepository.findAll();
        }

        public Optional<Apartment> getApartmentById(Long id) {
            return apartmentRepository.findById(id);
        }

        public void removeApartmentById(Long id) {
            apartmentRepository.deleteById(id);
        }

        public void addApartment(Apartment apartment) {
            apartmentRepository.save(apartment);
        }

        public void update(Apartment apartment) {
            apartmentRepository.save(apartment);
        }
    }
