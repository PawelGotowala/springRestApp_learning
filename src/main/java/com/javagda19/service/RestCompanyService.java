package com.javagda19.service;


import com.javagda19.mapper.CompanyMapper;
import com.javagda19.model.Company;
import com.javagda19.model.Employee;
import com.javagda19.model.dto.CompanyDto;
import com.javagda19.repository.CompanyRepository;
import com.javagda19.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestCompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private EmployeeRepository employeeRepository;


    public void create(CompanyDto companyDto) {
        companyRepository.save(companyMapper.companyDtoToCompany(companyDto));
    }


    public CompanyDto getById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(!companyOptional.isPresent()){
            throw new EntityNotFoundException();
        }
        return companyMapper.companyToCompanyDto(companyOptional.get());
    }


    public List<CompanyDto> getAll() {
        return companyRepository.findAll().stream()
                .map(companyMapper::companyToCompanyDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public void addEmployee(Long companyId, Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);

        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if(!companyOptional.isPresent()){
            throw new EntityNotFoundException("Company does not exist.");
        }
        Company company = companyOptional.get();
        company.getEmployees().add(employee);

        companyRepository.save(company);
    }
    //todo: dodac liste do companyDto i o co tam kaman plus wspolne listowanie
}
