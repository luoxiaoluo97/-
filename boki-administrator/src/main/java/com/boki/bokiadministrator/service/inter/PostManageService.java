package com.boki.bokiadministrator.service.inter;

import com.boki.bokiapi.entity.dto.request.PostSetTopDTO;
import com.boki.bokiapi.entity.dto.request.PostUpgradeDTO;
import com.boki.bokiapi.entity.dto.request.ReportJudgeDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.StoreyReplyVO;

import java.util.ArrayList;

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
     * 获取帖子列表
     * @return
     */
    DataWithTotal findPosts(Integer type,Integer page);

    /**
     * 帖子详情
     * @param postId
     * @return
     */
    PostDetailVO getPostDetail(Long postId, Integer page);

    /**
     * 加载楼中楼
     */
    ArrayList<StoreyReplyVO> findStoreyReplyById(Long replyId, Integer page);

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
