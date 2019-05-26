package com.softplan.interview.pessoas.model;

import org.springframework.http.HttpStatus;

public class NegocioResponse {

    private String message;
    private HttpStatus status;

    public NegocioResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}