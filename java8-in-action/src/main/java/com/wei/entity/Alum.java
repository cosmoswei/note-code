package com.wei.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 专辑
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Alum {
    /**
     * 专辑名
     */
    private String name;
    /**
     * 曲目
     */
    private List<Track> tracks;
    /**
     * 艺术家列表
     */
    private List<Artist> musicians;
}
