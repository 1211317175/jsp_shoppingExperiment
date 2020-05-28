package com.study.entity;

import java.util.Date;

/**
 * @author lxy
 * @date 2020-05-27-8:39
 * @function
 **/
public class OrderForm {

    private Long id;
    private String serial_number;
    private String username;
    private String item_name;
    private Double item_singlePrice;
    private Integer item_num;
    private Double item_totalPrice;
    private Date create_time;

    @Override
    public String toString() {
        return "OrderForm{" +
                "id=" + id +
                ", serial_number='" + serial_number + '\'' +
                ", username='" + username + '\'' +
                ", item_name='" + item_name + '\'' +
                ", item_singlePrice=" + item_singlePrice +
                ", item_num=" + item_num +
                ", item_totalPrice=" + item_totalPrice +
                ", create_time=" + create_time +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getItem_singlePrice() {
        return item_singlePrice;
    }

    public void setItem_singlePrice(Double item_singlePrice) {
        this.item_singlePrice = item_singlePrice;
    }

    public Integer getItem_num() {
        return item_num;
    }

    public void setItem_num(Integer item_num) {
        this.item_num = item_num;
    }

    public Double getItem_totalPrice() {
        return item_totalPrice;
    }

    public void setItem_totalPrice(Double item_totalPrice) {
        this.item_totalPrice = item_totalPrice;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
