package com.wei.aviator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
public class User {

    private int id;

    private String name;

    private String age;

    private Date birth;

    public User() {
    }
}