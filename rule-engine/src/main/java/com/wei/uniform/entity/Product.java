package com.wei.uniform.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Product {

    private String productId; // 商品ID
    private String name; // 商品名称
    private String description; // 商品描述
    private String category; // 分类
    private double price; // 价格
    private int stockQuantity; // 库存数量
    private Date releaseDate; // 上市日期
    private String manufacturer; // 制造商
    private String model; // 型号
    private double weight; // 重量
    private String material; // 材质
    private List<String> features; // 特征列表
    private Map<String, String> specifications; // 规格参数
    private String mainImageUrl; // 主图URL
    private List<String> imageUrls; // 图片URL列表
    private List<String> tags; // 标签列表
    private List<String> reviews; // 用户评价列表
    private double averageRating; // 平均评分
    private String shippingInfo; // 物流信息
    private double discountPercentage; // 折扣百分比
    private String warranty; // 保修信息
    private String countryOfOrigin; // 原产国
}