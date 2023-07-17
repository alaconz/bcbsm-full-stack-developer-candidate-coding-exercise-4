package com.bcbsm.ex4.controller.interfaces;

import com.bcbsm.ex4.model.LoginDto;
import com.bcbsm.ex4.model.RegisterDto;
import com.bcbsm.ex4.model.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    @PostMapping("login")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<Object> login(@RequestBody LoginDto loginDto);

    @PostMapping("register")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<RegisterResponse> register(@RequestBody RegisterDto registerDto);
}
