package com.boki.bokiadministrator.service.inter;

import com.boki.bokiapi.entity.dto.request.PostSetTopDTO;
import com.boki.bokiapi.entity.dto.request.PostUpgradeDTO;
import com.boki.bokiapi.entity.dto.request.ReportJudgeDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;

public interface PostManageService {

    /**
     * 举报列表
     * @param type 帖子，回复，二级回复
     * @param page
     * @return
     */
    DataWithTotal getReportList(String type,Integer page);

    /**
     * 驳回举报
     */
    Integer reportReject(ReportJudgeDTO dto);

    /**
     * 举报完成，删帖处理
     */
    Integer reportPass(ReportJudgeDTO dto);



    /**
     * 加精或降级
     */
    Integer postUpgrade(PostUpgradeDTO dto);

    /**
     * 设置置顶
     */
    Integer postSetTop(PostSetTopDTO dto);

    /**
     * 取消置顶
     */
    Integer postCancelTop(Long postId,Long modifier);
}
