package com.tau.rest3d.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class User {


    private MultipartFile stlFile;
    private int electricityCost;
    private int plasticCost;
    private String printerType;
    private float cost;

    protected User(){}

    public User(MultipartFile stlFile, int electricityCost, int plasricCost, String printerType, float cost) {
        this.stlFile = stlFile;
        this.electricityCost = electricityCost;
        this.plasticCost = plasricCost;
        this.printerType = printerType;
        this.cost = cost;
    }
}
