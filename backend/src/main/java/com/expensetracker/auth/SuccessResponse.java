package com.expensetracker.auth;

import lombok.Getter;

@Getter
public class SuccessResponse<T> {
    private final boolean success;

    private final T data;

    public SuccessResponse(T data) {
        this.success = true;
        this.data = data;
    }
}
