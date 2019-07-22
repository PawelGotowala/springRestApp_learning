package com.javagda19.controller;

import com.javagda19.model.dto.ApartmentDto;
import com.javagda19.model.dto.ApartmentUpdateRequest;
import com.javagda19.model.dto.ApartmentUpdateResult;
import com.javagda19.model.dto.CreateApartmentDto;
import com.javagda19.service.RestApartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "apartmentapi", description = "Operations on Your apartments.")
@RequestMapping(path = "/api/v2/apartment/")
public class ApiApartmentController {
    @Autowired
    private RestApartmentService restApartmentService;

    @PutMapping(path = "/addDefault")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addApartment(@RequestBody CreateApartmentDto apartmentDto) {
        restApartmentService.createDefault(apartmentDto);
    }

    @PostMapping(path = "/addTenant/{apartment_id}/{tenant_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTenantToApartment(@PathVariable(name = "apartment_id") Long apartmentId,
                                     @PathVariable(name = "tenant_id") Long tenantId) {
        // AddTenantToApartmentDto
        restApartmentService.addTenant(apartmentId, tenantId);
    }

    @GetMapping(path = "/getWithTenants/{id}")
    public ApartmentDto getWithTenants(@PathVariable(name = "id") Long id) {
        return restApartmentService.getApartmentWithTenants(id);
    }

    @GetMapping(path = "/list")
    public List<ApartmentDto> getApartmentList() {
        return restApartmentService.getAll();
    }

    @ApiOperation(value = "Get Apartment by id.", response = ApartmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved radar by it's id."),
            @ApiResponse(code = 400, message = "Entity not found."),
            @ApiResponse(code = 404, message = "The resource is not available.")
    })
    @GetMapping(path = "/getById/{identifier}")
    public ApartmentDto getById(@PathVariable(name = "identifier") Long id) {
        return restApartmentService.getById(id);
    }

    @DeleteMapping(path = "/delete/{ident}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable(name = "ident") Long id) {
        restApartmentService.removeById(id);
    }

    @PostMapping(path = "/update/{identifier}")
    public ApartmentUpdateResult update(@PathVariable(name = "identifier") Long id,
                                        @RequestBody ApartmentUpdateRequest updateRequest) {
        ApartmentDto apartmentDto = restApartmentService.update(id, updateRequest);

        return ApartmentUpdateResult.createFrom(id, updateRequest, apartmentDto);
    }
}
