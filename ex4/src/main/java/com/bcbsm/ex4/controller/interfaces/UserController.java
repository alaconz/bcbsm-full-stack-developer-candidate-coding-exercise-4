package com.bcbsm.ex4.controller.interfaces;

import com.bcbsm.ex4.entity.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {
    @GetMapping("/ratings")
    @PreAuthorize("hasAuthority('user')")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Rating> rate(@RequestHeader("Authorization") String authorizationHeader,
                      Authentication authentication);

    @PostMapping("/rate")
    @PreAuthorize("hasAuthority('user')")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<String> rate(@RequestHeader("Authorization") String authorizationHeader,
                                @RequestParam int rating,
                                @RequestParam String feedback,
                                Authentication authentication);
}
