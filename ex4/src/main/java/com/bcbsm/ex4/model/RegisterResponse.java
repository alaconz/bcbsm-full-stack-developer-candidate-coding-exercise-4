package com.bcbsm.ex4.model;

public record RegisterResponse(String response) {
    public RegisterResponse() {
        this("UserName Taken");
    }
}
