package com.example.demo.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: ljyang
 * @date: {2019/4/12} {15:30}
 * @description
 */
@Document(indexName = "demo-index", type = "_doc")
public class MsgEntity {
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Integer)
    private int idex;
    @Field(type = FieldType.Text)
    private String timestamp;

    public MsgEntity() {
    }

    public MsgEntity(String id, String name, int idex, String timestamp) {
        this.id = id;
        this.name = name;
        this.idex = idex;
        this.timestamp = timestamp;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MsgEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", idex=" + idex +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
