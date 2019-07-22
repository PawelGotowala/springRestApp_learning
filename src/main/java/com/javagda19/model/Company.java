package com.javagda19.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;

    @OneToMany
    private Set<Employee> employees;

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
