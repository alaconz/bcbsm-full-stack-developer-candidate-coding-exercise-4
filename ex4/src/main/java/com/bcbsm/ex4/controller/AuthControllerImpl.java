package com.bcbsm.ex4.controller;

import com.bcbsm.ex4.components.JWTGenerator;
import com.bcbsm.ex4.controller.interfaces.AuthController;
import com.bcbsm.ex4.model.*;
import com.bcbsm.ex4.repository.UserRepository;
import com.bcbsm.ex4.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthControllerImpl implements AuthController {

    private AuthenticationManager authenticationManager;
    private JWTGenerator jwtGenerator;

    private UserService userService;
    UserRepository userRepository;

    @Autowired
    public AuthControllerImpl(AuthenticationManager authenticationManager, UserService userService, UserRepository userRepository, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    @CrossOrigin(origins = "http://localhost:3000")
    @Override
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {

        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new LoginResponse("Unable to Authenticate"), HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("register")
    @CrossOrigin(origins = "http://localhost:3000")
    @Override
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>(new RegisterResponse(), HttpStatus.BAD_REQUEST);
        }
        String response = userService.registerUser(registerDto.getUsername(), registerDto.getPassword(), registerDto.getRole());
        return new ResponseEntity<>(new RegisterResponse(response), HttpStatus.OK);
    }

}
