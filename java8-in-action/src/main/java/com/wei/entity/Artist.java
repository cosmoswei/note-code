package com.wei.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 乐队
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Artist {
    /**
     * 乐队名字
     */
    private String name;
    /**
     * 乐队成员
     */
    private List<Member> members;
    /**
     * 乐队来自哪里
     */
    private String origin;
}
