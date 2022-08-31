package com.example.restapisample;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class profileController {
    public String getProfile(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "DOB") String DOB) { // DOB : day of birth 生年月日
        return "name:" + name + " " + "DOB" + DOB;
    }

}

