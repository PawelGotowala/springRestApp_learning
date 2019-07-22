package com.javagda19.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String address;

    @Length(min = 6, max = 6)
    private String zipcode;
    private String city;
    private Double size;
    private Integer rooms; //"localhost:8080/swagger-ui.html"

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastRenovation;

    private Double value;
    private Integer floor;
    private boolean nonSmoking;
    private boolean noPets;

    @OneToMany
    private Set<Tenant> tenants;

    @Column(name = "available")
    private boolean available;

    public Apartment(String address, String zipcode, String city, double size, int rooms, LocalDate lastRenovation, double value, int floor, boolean nonSmoking, boolean noPets, boolean available) {
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.size = size;
        this.rooms = rooms;
        this.lastRenovation = lastRenovation;
        this.value = value;
        this.floor = floor;
        this.nonSmoking = nonSmoking;
        this.noPets = noPets;
        this.available = available;
    }
}