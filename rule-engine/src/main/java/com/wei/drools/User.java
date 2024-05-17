package com.wei.drools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class User {

    private int id;

    private String name;

    private String age;

    public User() {
    }
}