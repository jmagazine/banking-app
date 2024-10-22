package com.example.banking.account.investment;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "investments")
public abstract class Investment {
    @Id
    @SequenceGenerator(name = "investments_sequence",
            sequenceName = "investments_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "investments_sequence")
    private java.lang.Long id;

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }


    public Investment() {
    }

    public Investment(java.lang.Long id) {
        this.id = id;

    }

}
