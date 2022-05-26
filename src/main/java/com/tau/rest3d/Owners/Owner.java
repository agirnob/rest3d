package com.tau.rest3d.Owners;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;

@EqualsAndHashCode
@ToString
@Setter
@Entity
@Table

public class Owner {

    @Id
    @SequenceGenerator(
            name = "owner_sequence",
            sequenceName = "owner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_sequence"
    )
    private long id;
    private String userName;
    private String email;
    private LocalDate dateOfSignUp;
    private String password;
    @Transient
    private Integer daysLeft;

    public Owner(String name, String email, LocalDate dateOfSignUp,String password ) throws NoSuchAlgorithmException {

        this.password = password;
        this.userName =name;
        this.dateOfSignUp = dateOfSignUp;
        this.email = email;
    }
    public Owner(long id, String name, String email, LocalDate dateOfSignUp,String password) throws NoSuchAlgorithmException {
        this.email= email;

        this.id = id;
        this.userName = name;
        this.dateOfSignUp =dateOfSignUp;
        this.password= password;
    }

    protected Owner(){}


    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfSignUp() {
        return dateOfSignUp;
    }

    public String getPassword() {
        //TODO make a hash code here
        return "password";
    }

    public Integer getDaysLeft() {
        return Period.between(this.dateOfSignUp,LocalDate.now()).getDays();
    }
}
