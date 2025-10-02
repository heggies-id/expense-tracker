package com.expensetracker.auth;

import lombok.Data;

@Data
public class ErrorResponse {
    private final boolean success;

    private final String message;

    public ErrorResponse(String message) {
        this.success = false;
        this.message = message;
    }
}
