package com.javagda19.controller;

import com.javagda19.model.dto.CompanyDto;
import com.javagda19.service.RestCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/api/v2/company/")
@RestController
public class ApiCompanyController {

    @Autowired
    private RestCompanyService restCompanyService;

    @PutMapping(path = "/add")
    @ResponseStatus(HttpStatus.OK)
    public void addCompany(@RequestBody CompanyDto companyDto){
        restCompanyService.create(companyDto);
    }

    @GetMapping(path = "/getById/{ide}")
    public CompanyDto getById(@PathVariable(name = "ide") Long id){
       return restCompanyService.getById(id);
    }

    @GetMapping(path = "/list")
    public List<CompanyDto> getAll(){
     return restCompanyService.getAll();
    }

    @PostMapping(path = "/addEmployee/{company_id}/{employee_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployeeToCompany(@PathVariable(name = "company_id") Long companyId,
                                     @PathVariable(name = "employee_id") Long employeeId) {
        // AddTenantToApartmentDto
        restCompanyService.addEmployee(companyId, employeeId);
    }


}
