package com.example.package_tracking.shipment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


import java.time.LocalDate;
import java.time.Period;

@Entity(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private String person;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate creation;
    @Transient
    private Integer monthsAgo;

    public Shipment() {
    }

    public Shipment(Long id, String product, String person, String email, Status status, LocalDate creation) {
        this.id = id;
        this.product = product;
        this.person = person;
        this.email = email;
        this.status = status;
        this.creation = creation;

    }

    public Shipment(String product, String person, String email, Status status, LocalDate creation) {
        this.product = product;
        this.person = person;
        this.email = email;
        this.status = status;
        this.creation = creation;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    public Integer getMonthsAgo() {
        return Period.between(this.creation, LocalDate.now()).getMonths();
    }

    public void setMonthsAgo(Integer age) {
        this.monthsAgo = monthsAgo;
    }

    @Override
    public String toString() {
        return "Shipment{" + "id=" + id + ", name='" + product + '\'' + ", person='" + person + '\'' + ", email='" + email + '\'' + ", status='" + status + '\'' + ", creation=" + creation + ", age=" + monthsAgo + '\'' + '}';
    }
}
