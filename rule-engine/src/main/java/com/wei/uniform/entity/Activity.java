package com.wei.uniform.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Activity {
    // 活动名称
    private String name;
    // 活动描述
    private String description;
    // 活动类型，如"折扣", "买赠", "限时抢购"等
    private String type;
    // 活动开始日期
    private Date startDate;
    // 活动结束日期
    private Date endDate;
    // 活动时间信息
    private String timeInfo;
    // 活动区域信息
    private String regionInfo;
    // 活动目标人群信息
    private String crowdInfo;
    // 活动优惠信息
    private String discountInfo;
    // 创建时间
    private Date tmRequest;
    // 创建人
    private String operatorId;
}