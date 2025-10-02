package com.expensetracker.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private final String name;
    private final String email;
    private final String password;
}
