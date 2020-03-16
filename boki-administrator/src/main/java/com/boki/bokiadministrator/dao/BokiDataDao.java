package com.boki.bokiadministrator.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BokiDataDao {

    /**
     * 获取一些数据，用于统计，以后可能会优化
     */
    List<List<?>> findSomeData();
}
