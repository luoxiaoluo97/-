package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.postdetail.PostDetailDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.ReplyVO;
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
    public PostDetailVO getPostDetail(Long postId) {
        PostDetailDTO dto = commonDao.getPostDetail(postId);
        ArrayList<ReplyDTO> replyList = commonDao.getReplyList(postId);
        PostDetailVO vo = new PostDetailVO();
        //复制属性到vo
        BeanUtils.copyProperties(dto,vo);
        vo.setLevel(dbSource.getLv(dto.getExp()));
        vo.setHonorId(dbSource.getHonorId(dto.getCreditDegree()));
        vo.setRole(dbSource.getRoleById(dto.getRoleId()));
        vo.setType(dbSource.getType(dto.getTypeId()));

        if( replyList != null) {
            vo.setStoreys(new ArrayList<>());
            for (int i = 0; i < replyList.size(); i++) {
                vo.getStoreys().add(new ReplyVO());
                BeanUtils.copyProperties(replyList.get(i), vo.getStoreys().get(i));
                //设置角色
                String role = dbSource.getRoleById(replyList.get(i).getRoleId());
                vo.getStoreys().get(i).setRole(role);
            }
        }
        return vo;
    }

}
