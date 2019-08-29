package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.DemoESDao;
import com.example.demo.entity.MsgEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: ljyang
 * @date: {2019/4/12} {13:58}
 * @description
 */
@Component
public class DemoService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private DemoESDao demoESDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @KafkaListener(topics = {"my-topic"}, groupId = "my-group")
    public void listen(ConsumerRecord<?, ?> record){
        System.out.println(record.key());
        byte[] bytes = (byte[]) record.value();
        String str = new String(bytes);
        System.out.println(str);
        MsgEntity msgEntity = null;

        try{
            msgEntity = JSON.parseObject(str, MsgEntity.class);
            System.out.println(msgEntity.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        if(msgEntity != null){
            //redis中记录（更新）在线离线状态，redis中值的有效期设为45s
            redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
            redisTemplate.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));

            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            hashOperations.put(msgEntity.getId(), "index", msgEntity.getIdex());
            hashOperations.put(msgEntity.getId(), "name", msgEntity.getName());
            redisTemplate.expire(msgEntity.getId(), 45, TimeUnit.SECONDS);

            System.out.println(hashOperations.get(msgEntity.getId(), "name"));

            System.out.println("write into redis");
            //如果ES数据库中没有信息则保存数据，有则更新数据。
            if (!demoESDao.existsById(msgEntity.getId())){
                demoESDao.save(msgEntity);
                System.out.println("+++" + msgEntity.toString());
            }
            String sql = "INSERT INTO `demo_info`(`id`,`name`,`idex`,`timestamp`) VALUE(?,?,?,?)";
            jdbcTemplate.update(sql, msgEntity.getId(), msgEntity.getName(), msgEntity.getIdex(), msgEntity.getTimestamp());
        }

        /*
        if (msgEntity != null){
            if(elasticsearchTemplate.indexExists(MsgEntity.class)) {
                GetQuery getQuery = new GetQuery();
                getQuery.setId(String.valueOf(msgEntity.getId()));
                MsgEntity queryMsg = elasticsearchTemplate.queryForObject(getQuery, MsgEntity.class);
                //queryMsg null
            }else{
                elasticsearchTemplate.createIndex(MsgEntity.class);
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setIndexName("demo-index");
                indexQuery.setType("_doc");
                indexQuery.setId(String.valueOf(msgEntity.getId()));
                indexQuery.setSource(JSON.toJSONString(msgEntity));
                elasticsearchTemplate.index(indexQuery);
            }
        }
        */

    }

}
