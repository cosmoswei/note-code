package com.wei.strategy.list;

import java.math.BigDecimal;

public interface LogisticsService {

    boolean isCurrentLogistics(Integer type);

    BigDecimal calculateFee(TransferFeeRequest transferFeeRequest);
}
