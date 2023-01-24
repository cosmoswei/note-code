package com.wei.map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
enum TaskState {
    INIT("初始化"),
    ONGOING( "进行中"),
    PAUSED("暂停中"),
    FINISHED("已完成"),
    EXPIRED("已过期")
    ;
    private final String message;
}
// 行为枚举
@AllArgsConstructor
@Getter
enum ActionType {
    START(1, "开始"),
    STOP(2, "暂停"),
    ACHIEVE(3, "完成"),
    EXPIRE(4, "过期")
    ;
    private final int code;
    private final String message;
}
