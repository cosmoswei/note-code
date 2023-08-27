package com.wei.util.mock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class People {
    private String name;
    public Integer id;
    private String code;
}
