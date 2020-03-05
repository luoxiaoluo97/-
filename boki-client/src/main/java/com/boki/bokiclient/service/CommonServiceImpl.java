package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.postdetail.PostDetailDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import com.boki.bokiapi.entity.dto.postdetail.StoreyReplyDTO;
import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.ReplyVO;
import com.boki.bokiapi.entity.vo.postdetail.StoreyReplyVO;
import com.boki.bokiclient.bean.DBSourceSelectBean;
import com.boki.bokiclient.dao.CommonDao;
import com.boki.bokiclient.service.inter.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DBSourceSelectBean dbSource;

    @Override
    public PostDetailVO getPostDetail(Long postId,Integer page) {
        PostDetailDTO dto = commonDao.getPostDetail(postId);
        ArrayList<ReplyDTO> replyList = commonDao.getReplyList(postId,(page-1)*20,20);
        PostDetailVO vo = new PostDetailVO();
        //复制属性到vo
        BeanUtils.copyProperties(dto,vo);
        //帖子类型
        vo.setType(dbSource.getType(dto.getTypeId()));

        if( replyList != null) {
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
    public ArrayList<StoreyReplyVO> findStoreyReplyById(Long replyId,Integer page) {
        ArrayList<StoreyReplyDTO> dtoList = commonDao.findStoreyReplyById(replyId,(page-1)*20,20);
        ArrayList<StoreyReplyVO> voList = null;
        if (dtoList != null) {
            voList = new ArrayList<>();
            for (int i = 0; i < dtoList.size(); i++) {
                voList.add(new StoreyReplyVO());
                BeanUtils.copyProperties(dtoList.get(i), voList.get(i));
            }
        }
        return voList;
    }

}
