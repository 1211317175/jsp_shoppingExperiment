package com.study.entity;

/**
 * @author lxy
 * @date 2020-05-26-7:56
 * @function
 **/
public class ShoppingTrolley {

    private Integer id;
    private Integer item_id;
    private String item_name;
    private Double item_singlePrice;
    private Integer item_num;
    private String username;

    @Override
    public String toString() {
        return "ShoppingTrolley{" +
                "id=" + id +
                ", item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", singlePrice=" + item_singlePrice +
                ", item_num=" + item_num +
                ", username='" + username + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getItem_num() {
        return item_num;
    }

    public void setItem_num(Integer item_num) {
        this.item_num = item_num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getItem_singlePrice() {
        return item_singlePrice;
    }

    public void setItem_singlePrice(Double item_singlePrice) {
        this.item_singlePrice = item_singlePrice;
    }
}
