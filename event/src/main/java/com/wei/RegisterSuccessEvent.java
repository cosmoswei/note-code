package com.wei;

import lombok.Data;

@Data
public class RegisterSuccessEvent {

    private String userName;

    public RegisterSuccessEvent(String userName) {
        this.userName = userName;
    }
}