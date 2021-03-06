package com.boki.bokiadministrator.dao;

import com.boki.bokiapi.entity.dto.request.PostSetTopDTO;
import com.boki.bokiapi.entity.dto.request.PostUpgradeDTO;
import com.boki.bokiapi.entity.dto.request.ReportJudgeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostManageDao {

    /**
     * 获取被举报帖子列表
     */
    List<List<?>> findReports(@Param("type")String type,@Param("start")Integer start,@Param("end")Integer end);

    /**
     * 驳回举报
     */
    Integer updateToReportReject(ReportJudgeDTO dto);

    /**
     * 举报通过，删帖处理
     */
    Integer updateToReportPass(ReportJudgeDTO dto);



    /**
     * 更改帖子类型，普通贴 精华贴
     */
    Integer updatePostType(PostUpgradeDTO dto);


    /**
     * 更新置顶与结束时间
     */
    Integer updateToSetTop(PostSetTopDTO dto);

    /**
     * 取消置顶
     */
    Integer updateToCancelTop(@Param("postId")Long postId,@Param("modifier")Long modifier);
}
