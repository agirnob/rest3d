package com.tau.rest3d.users;

import com.tau.rest3d.PrintInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public @ResponseBody PrintInfo uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
       // File fileNotMulti = new File(file);
        String[] str = userService.uploadService(file);

        return new PrintInfo(str[0],str[1],str[2]);



    }


}

