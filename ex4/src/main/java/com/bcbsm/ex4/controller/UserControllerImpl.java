package com.bcbsm.ex4.controller;

import com.bcbsm.ex4.controller.interfaces.UserController;
import com.bcbsm.ex4.entity.Rating;
import com.bcbsm.ex4.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user/")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class UserControllerImpl implements UserController {

    final UserService userService;


    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ratings")
    @PreAuthorize("hasAuthority('user')")
    @CrossOrigin(origins = "http://localhost:3000")
    @Override
    public List<Rating> rate(@RequestHeader("Authorization") String authorizationHeader,
                             Authentication authentication) {
        String username = authentication.getName();
        return userService.getRatings(username);

    }

    @PostMapping("/rate")
    @PreAuthorize("hasAuthority('user')")
    @CrossOrigin(origins = "http://localhost:3000")
    @Override
    public ResponseEntity<String> rate(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestParam int rating,
                                       @RequestParam String feedback,
                                       Authentication authentication) {
        String username = authentication.getName();
        return new ResponseEntity<>(userService.rate(rating, feedback, username), HttpStatus.OK);
    }
}
