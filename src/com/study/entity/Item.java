package com.study.entity;

/**
 * @author lxy
 * @date 2020-05-12-16:18
 * @function
 **/
public class Item {
    private Integer id;
    private String name;
    private Double price;
    private String portrait;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", portrait='" + portrait + '\'' +
                '}';
    }

    public Item(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
