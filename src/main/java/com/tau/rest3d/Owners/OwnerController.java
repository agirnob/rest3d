package com.tau.rest3d.Owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/owner")
public class OwnerController {

    private final OwnerService ownerService;


    public OwnerController(OwnerService ownerService){this.ownerService=ownerService;}

    @GetMapping
    public List<Owner> getOwners(){return ownerService.getOwners();}

}
