package com.javagda19.service;

import com.javagda19.mapper.EmployeeMapper;
import com.javagda19.model.Employee;
import com.javagda19.model.dto.EmployeeDto;
import com.javagda19.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.javagda19.util.ModelUtility.copyNonNullProperties;

@Service
public class RestEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;


    public void create(EmployeeDto employeeDto) {
        employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto));
    }

    public List<EmployeeDto> getAll() {
       return employeeRepository.findAll().stream()
               .map(employeeMapper ::employeeToEmployeeDto)
               .collect(Collectors.toList());
    }

    public EmployeeDto getById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeMapper.employeeToEmployeeDto(employeeOptional.orElseThrow(EntityNotFoundException::new));
    }

    public void removeById(Long id) { employeeRepository.deleteById(id); }


    public EmployeeDto update(Long id , EmployeeDto updateRequest) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new EntityNotFoundException("Tenant with this id does not exist.");
        }

        Employee employee = employeeOptional.get();
        copyNonNullProperties(updateRequest, employee);

        employeeRepository.save(employee); // zapisz zmiany do bazy

        return employeeMapper.employeeToEmployeeDto(employee);
    }

}
