package com.javagda19.controller;

import com.javagda19.model.dto.TenantDto;
import com.javagda19.service.RestTenantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/tenant/")
public class ApiTenantController {

    @Autowired
    private RestTenantService restTenantService;

    @PutMapping(path = "/addDefault")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addTenant(@RequestBody TenantDto tenantDto) {
        restTenantService.create(tenantDto);
    }

    @GetMapping(path = "/list")
    public List<TenantDto> getTenantList() {
        return restTenantService.getAll();
    }

    @ApiOperation(value = "Get Tenant by id.", response = TenantDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved radar by it's id."),
            @ApiResponse(code = 400, message = "Entity not found."),
            @ApiResponse(code = 404, message = "The resource is not available.")
    })
    @GetMapping(path = "/getById/{identifier}")
    public TenantDto getById(@PathVariable(name = "identifier") Long id) {
        return restTenantService.getById(id);
    }

    @DeleteMapping(path = "/delete/{ident}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable(name = "ident") Long id) {
        restTenantService.removeById(id);
    }

    @PostMapping(path = "/update/{identifier}")
    @ResponseStatus(HttpStatus.OK)
    public TenantDto update(@PathVariable(name = "identifier") Long id,
                            @RequestBody TenantDto updateRequest) {
        return restTenantService.update(id, updateRequest);
    }

}