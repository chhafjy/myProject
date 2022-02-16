package com.example.mainmodule.test.beans;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 *
 * @author chenhaohao
 * @version 1.0
 * @date 2021/11/19 10:16
 */
@Data
public class BeanOne {
    private String name;
    private Integer age;
    private String address;

    public BeanOne(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public BeanOne() {
    }

    public BeanOne(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "BeanOne{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
