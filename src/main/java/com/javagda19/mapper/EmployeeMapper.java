package com.javagda19.mapper;


import com.javagda19.model.Employee;
import com.javagda19.model.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(target = "name" , source = "name"),
            @Mapping(target = "surname", source = "surname"),
            @Mapping(target = "pay", source = "pay"),
            @Mapping(target = "employmentDate" , source = "employmentDate"),
            @Mapping(target = "birthDate" , source = "birthDate"),
    })
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    @Mappings({
            @Mapping(source = "name" , target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "pay", target = "pay"),
            @Mapping(source = "employmentDate" , target = "employmentDate"),
            @Mapping(source = "birthDate" , target = "birthDate"),
    })
    EmployeeDto employeeToEmployeeDto(Employee employee);

}
