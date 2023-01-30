package com.wei.strategy.list;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransferFeeRequest {

    /**
     * 距离
     */
    private BigDecimal distance;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 快递类型
     */
    private Integer type;
}