package com.example.demo.dao;

import com.example.demo.entity.DemoInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: ljyang
 * @date: 2019/9/2 18:27
 * @description
 */

@Mapper
public interface DemoInfoDao {
    public List<DemoInfoEntity> getDemoInfo() throws Exception;
    public void deleteDemoInfo(int id)throws Exception;
    public void addDemoInfo(DemoInfoEntity demoInfoEntity)throws Exception;
}
