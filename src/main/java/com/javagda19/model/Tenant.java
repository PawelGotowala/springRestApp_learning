package com.javagda19.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private String documentId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementEndDate;
    private Double rent;
    private Double deposited;
    private int paymentDayOfMonth;

    public Tenant(String name, String surname, String documentId, LocalDate agreementStartDate, LocalDate agreementEndDate, double rent, double deposited, int paymentDayOfMonth) {
        this.name = name;
        this.surname = surname;
        this.documentId = documentId;
        this.agreementStartDate = agreementStartDate;
        this.agreementEndDate = agreementEndDate;
        this.rent = rent;
        this.deposited = deposited;
        this.paymentDayOfMonth = paymentDayOfMonth;
    }
}