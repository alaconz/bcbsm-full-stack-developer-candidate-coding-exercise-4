package com.bcbsm.ex4.model;

public record LoginResponse(String response) {
    public LoginResponse() {
        this("Logged In");
    }
}
