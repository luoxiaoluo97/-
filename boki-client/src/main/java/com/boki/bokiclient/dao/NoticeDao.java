package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.po.NoticePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: LJF
 * @Date: 2020/3/10
 * @Description:
 */
@Mapper
@Repository
public interface NoticeDao {

    /**
     * 新增一条通知
     */
    Integer insertNotice(NoticePO po);

    /**
     * id最新发帖
     */
    Long findLastPostId(@Param("userId")Long userId,@Param("title")String title);

    /**
     * 批量新增发帖动态通知
     */
    Integer multiInsertNotice(List<NoticePO> pos);

    /**
     * 获取发帖时的楼层与标题，楼主id
     */
    Map findReplyNoticeElem(@Param("userId")Long userId,@Param("content")String content);

    /**
     * 获取楼层回复时的楼层与帖子标题，层主id
     */
    Map findStoreyReplyNoticeElem(@Param("storeyId")Long storeyId);

    /**
     * 检查用户名是否存在
     */
    Long findIdByUserName(@Param("userName") String userName);

    /**
     * 根据用户名批量查询用户id
     */
    List<?> findIdByUserNameList(@Param("userList") List<String> userList);

    /**
     * 通知列表
     */
    List<List<?>> findNoticeByUid(@Param("userId")Long userId,
                                  @Param("start")Integer start,
                                  @Param("end")Integer end);

    /**
     * 移除某项通知
     */
    Integer deleteNoticeById(@Param("userId") Long userId,@Param("id")Integer id);

    /**
     * 清空通知
     */
    Integer deleteAllNotice(@Param("userId") Long userId);
}
