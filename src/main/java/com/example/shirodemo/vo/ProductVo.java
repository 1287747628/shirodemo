package com.example.shirodemo.vo;

import javax.validation.constraints.NotBlank;

/**
 * @author jocken
 * @date 2021/3/17
 */
public class ProductVo {

    @NotBlank(message = "productId cannot be empty")
    private String productId;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "type cannot be empty")
    private String type;
    private Integer price = 0;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
