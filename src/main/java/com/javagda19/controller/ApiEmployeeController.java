package com.javagda19.controller;

import com.javagda19.model.dto.EmployeeDto;
import com.javagda19.service.RestEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v2/employee/")
public class ApiEmployeeController {

    @Autowired
    RestEmployeeService restEmployeeService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping(path = "/addDefault")
    public void addEmployee(@RequestBody EmployeeDto employeeDto){restEmployeeService.create(employeeDto);}

    @GetMapping(path = "/list")
    public List<EmployeeDto> getEmployeeList (){ return restEmployeeService.getAll() ;}


    @GetMapping(path = "/getById/{identifier}")
    public EmployeeDto getById(@PathVariable(name = "identifier") Long id) {
        return restEmployeeService.getById(id);
    }

    @DeleteMapping(path = "/deleteById/{iden}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable(name = "iden") Long id){ restEmployeeService.removeById(id);}

    @PostMapping(path = "updateById/{identi}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateById(@PathVariable(name = "identi") Long id,
                           @RequestBody EmployeeDto updateRequest) {
     return restEmployeeService.update(id,updateRequest);
    }


}
