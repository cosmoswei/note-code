package com.wei;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String test() {
        return String.valueOf(System.currentTimeMillis());
    }
}
