package com.wei.strategy.list.impl;

import com.wei.strategy.list.LogisticsService;
import com.wei.strategy.list.TransferFeeRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class SFTransfercompany implements LogisticsService {

    private BigDecimal pickFee = BigDecimal.TEN;

    private BigDecimal minDistance = BigDecimal.valueOf(60);

    @Override
    public BigDecimal calculateFee(TransferFeeRequest transferFeeRequest) {
        BigDecimal distance = minDistance.compareTo(transferFeeRequest.getDistance()) > 0 ?
                minDistance : transferFeeRequest.getDistance();
        BigDecimal fee = distance.multiply(transferFeeRequest.getUnitPrice()).add(pickFee);
        return fee;
    }
    @Override
    public boolean isCurrentLogistics(Integer type) {
        return Objects.equals(type, 2);
    }
}
