package com.wei.strategy.list.impl;

import com.wei.strategy.list.LogisticsService;
import com.wei.strategy.list.TransferFeeRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class YZTransfercompany implements LogisticsService {

    private BigDecimal pickFee = BigDecimal.TEN;

    private BigDecimal minDistance = BigDecimal.valueOf(40);

    @Override
    public boolean isCurrentLogistics(Integer type) {
        return Objects.equals(type, 9);
    }

    @Override
    public BigDecimal calculateFee(TransferFeeRequest transferFeeRequest) {
        System.out.println("使用邮政快递！");
        BigDecimal distance = minDistance.compareTo(transferFeeRequest.getDistance()) > 0 ?
                minDistance : transferFeeRequest.getDistance();
        // do business
        return distance.multiply(transferFeeRequest.getUnitPrice()).add(pickFee);
    }
}
