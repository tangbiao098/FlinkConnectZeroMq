package com.cetc.entity;

import com.alibaba.fastjson.JSON;

public class Student {
    private String name;
    private double hight;
    private double weight;
    private int age;

    public Student() {
    }


    public Student(String name, double hight, double weight, int age) {
        this.name = name;
        this.hight = hight;
        this.weight = weight;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHight() {
        return hight;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
