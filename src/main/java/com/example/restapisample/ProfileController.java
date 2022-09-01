package com.example.restapisample;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
public class ProfileController {
    @GetMapping("/profile")
    public String getProfile(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "day_of_birth") String day_of_birth) {
        return "name:" + name + " " + "day_of_birth:" + day_of_birth;
    }

    @PostMapping("/names")
    public ResponseEntity<String> create(@RequestBody @Valid CreateForm form) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/names/id")
                .build()
                .toUri();
        return ResponseEntity.created(url).body("name successfully created");
    }
}



