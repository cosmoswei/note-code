package com.wei.uniform;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class UniformRequest {

    /**
     * 请求渠道
     */
    private String requestChannel;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 请求参数
     */
    private Map<String, Object> requestParam;
    /**
     * 请求时间
     */
    private final Date requestTime = new Date();


}
