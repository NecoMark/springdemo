package com.example.demo.dao;

import com.example.demo.entity.MsgEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.management.ObjectInstance;
import java.io.Serializable;

/**
 * @author: ljyang
 * @date: {2019/4/12} {17:36}
 * @description
 */
public interface DemoESDao extends ElasticsearchRepository<MsgEntity, String> {
}
