package com.boki.bokiadministrator.service;

import com.boki.bokiadministrator.bean.DBSourceSelectBean;
import com.boki.bokiadministrator.dao.PostManageDao;
import com.boki.bokiadministrator.service.inter.PostManageService;
import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.dto.ReportInfoDTO;
import com.boki.bokiapi.entity.dto.postdetail.PostDetailDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import com.boki.bokiapi.entity.dto.postdetail.StoreyReplyDTO;
import com.boki.bokiapi.entity.dto.request.ReportJudgeDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.PostVO;
import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.ReplyVO;
import com.boki.bokiapi.entity.vo.postdetail.StoreyReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/15
 * @Description:
 */
@Slf4j
@Service
@Transactional
public class PostManageServiceImpl implements PostManageService {


    @Autowired
    private PostManageDao postManageDao;

    @Autowired
    private DBSourceSelectBean dbSource;

    @Override
    public DataWithTotal getReportList(String type, Integer page) {
        List<List<?>> result = postManageDao.findReports(type,(page-1)*15,15);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result, ReportInfoDTO.class);
        for (Object o : vo.getList()){
            ((ReportInfoDTO)o).setType(type);
        }
        return vo;
    }

    @Override
    public Integer reportReject(ReportJudgeDTO dto) {
        int count = postManageDao.updateToReportReject(dto);
        return count;
    }

    @Override
    public Integer reportPass(ReportJudgeDTO dto) {
        int count = postManageDao.updateToReportPass(dto);
        return count;
    }

    @Override
    public DataWithTotal findPosts(Integer page) {
        List<List<?>> result = postManageDao.findPosts((page-1)*30,30);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result, PostVO.class);
        //复制List属性
        if( vo.getList() != null && vo.getList().size() != 0) {
            for (int i = 0;i < vo.getList().size(); i++) {
                PostVO postVO = (PostVO)(vo.getList().get(i));
                //vo属性，帖子最后回复时间
                String lastReplyTime = ((PostDTO)(result.get(1).get(i))).getModifiedTime();
                postVO.setLastReplyTime(lastReplyTime);
                //vo属性，帖子类型
                Integer typeId = ((PostDTO)(result.get(1).get(i))).getTypeId();
                postVO.setType(dbSource.getType(typeId));
            }
        }
        return vo;
    }

    @Override
    public PostDetailVO getPostDetail(Long postId, Integer page) {
        List<List<?>> result = postManageDao.getPostDetail(postId,(page-1)*15,15);
        PostDetailVO vo = new PostDetailVO();
        //帖子基本信息
        BeanUtils.copyProperties(result.get(0).get(0),vo);
        //楼层总数
        vo.setTotalCount( (Integer)(result.get(1).get(0)) );
        Integer typeId = ((PostDetailDTO)(result.get(0).get(0))).getTypeId();
        vo.setType(dbSource.getType(typeId));
        //楼层列表
        if( result.get(2) != null && result.get(2).size() != 0){
            List<ReplyDTO> replyList = (List<ReplyDTO>)(result.get(2));
            vo.setStoreys(new ArrayList<>());
            for (int i = 0; i < replyList.size(); i++) {
                vo.getStoreys().add(new ReplyVO());
                BeanUtils.copyProperties(replyList.get(i), vo.getStoreys().get(i));
                //设置vo属性
                String role = dbSource.getRoleById(replyList.get(i).getRoleId());
                Integer lv = dbSource.getLv(replyList.get(i).getExp());
                Integer honorId = dbSource.getHonorId(replyList.get(i).getCreditDegree());
                vo.getStoreys().get(i).setRole(role)
                        .setLevel(lv)
                        .setHonorId(honorId);
            }
        }
        return vo;
    }

    @Override
    public ArrayList<StoreyReplyVO> findStoreyReplyById(Long replyId, Integer page) {
        ArrayList<StoreyReplyDTO> dtoList = postManageDao.findStoreyReplyById(replyId,(page-1)*20,20);
        ArrayList<StoreyReplyVO> voList = null;
        if (dtoList != null && dtoList.size() != 0) {
            voList = new ArrayList<>();
            for (int i = 0; i < dtoList.size(); i++) {
                voList.add(new StoreyReplyVO());
                BeanUtils.copyProperties(dtoList.get(i), voList.get(i));
            }
        }
        return voList;
    }
}
