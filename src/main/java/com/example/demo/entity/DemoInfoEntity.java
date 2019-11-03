package com.example.demo.entity;

import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * @author: ljyang
 * @date: 2019/9/2 18:24
 * @description
 */

@Component
@ToString
public class DemoInfoEntity {
    private int id;
    private String name;
    private int idex;
    private String timestamp;

    public DemoInfoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdex() {
        return idex;
    }

    public void setIdex(int idex) {
        this.idex = idex;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
