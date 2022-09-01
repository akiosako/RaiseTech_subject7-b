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
            @RequestParam(name = "dayOfBirth") String dayOfBirth) {
        return "name:" + name + " " + "dayOfBirth:" + dayOfBirth;
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



