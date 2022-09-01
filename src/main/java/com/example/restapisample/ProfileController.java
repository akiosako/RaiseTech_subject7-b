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
<<<<<<< HEAD
            @RequestParam(name = "dayOfBirth") String dayOfBirth) {
        return "name:" + name + " " + "dayOfBirth:" + dayOfBirth;
=======
            @RequestParam(name = "day_of_birth") String dayOfBirth) {
        return "name:" + name + " " + "day_of_birth:" + day_of_birth;
>>>>>>> d7ba7e0498078dbe6b74a816000129debbced975
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



