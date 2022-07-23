package com.wei.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class OrderItem {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
}
