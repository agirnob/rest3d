package com.tau.rest3d.users;

import com.tau.rest3d.PrintInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping

    public @ResponseBody PrintInfo uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        PrintInfo printInfo= new PrintInfo(userService.uploadService(file)[0],userService.uploadService(file)[1],userService.uploadService(file)[2]);

        return printInfo;



    }


}

