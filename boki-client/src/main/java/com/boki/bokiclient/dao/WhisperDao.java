package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.po.WhisperDetailPO;
import com.boki.bokiapi.entity.po.WhisperPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WhisperDao {

    /**
     * 若不存在，创建私信
     */
    Integer insertWhisper(WhisperPO po);

    /**
     * 打开会话
     */
    Integer updateWhisperOnShow(WhisperPO po);

    /**
     * 会话内容
     */
    List<List<?>> findWhisper(WhisperPO po);


    /**
     * 插入一条私信记录
     * @param po
     * @return
     */
    Integer insertWhisperDetail(WhisperDetailPO po);

    /**
     * 私信列表
     */
    List<List<?>> getWhisperList(@Param("userId")Long userId,
                                 @Param("start")Integer start,
                                 @Param("end")Integer end);

    /**
     * 移除悄悄话
     */
    Integer removeWhisper(WhisperPO po);
}
