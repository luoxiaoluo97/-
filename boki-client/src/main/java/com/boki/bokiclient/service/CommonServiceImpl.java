package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.dto.postdetail.PostDetailDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import com.boki.bokiapi.entity.dto.postdetail.StoreyReplyDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.PostHistoryVO;
import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.ReplyVO;
import com.boki.bokiapi.entity.vo.postdetail.StoreyReplyVO;
import com.boki.bokiclient.bean.DBSourceSelectBean;
import com.boki.bokiclient.dao.CommonDao;
import com.boki.bokiclient.dao.UserDao;
import com.boki.bokiclient.service.inter.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */
@Slf4j
@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DBSourceSelectBean dbSource;

    @Autowired
    private UserDao userDao;

    @Override
    public PostDetailVO getPostDetail(Long postId,Integer page) {
        List<List<?>> result = commonDao.getPostDetail(postId,(page-1)*15,15);
        if(result.get(0).size() == 0){
            return null;
        }
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
    public ArrayList<StoreyReplyVO> findStoreyReplyById(Long replyId,Integer page) {
        ArrayList<StoreyReplyDTO> dtoList = commonDao.findStoreyReplyById(replyId,(page-1)*5,5);
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

    @Override
    public DataWithTotal getUserLastPosts(Long userId) {
        // 近72小时
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-3);
        String threeDaysAgo = sdf.format(calendar.getTime());

        List<List<?>> result = userDao.findUserLastPosts(userId,threeDaysAgo,null,null);
        for (Object dto : result.get(1)){
            PostDTO postDTO = ((PostDTO)dto);
            ((PostDTO)dto).setType(dbSource.getType(postDTO.getTypeId()));
        }
        DataWithTotal vo = new DataWithTotal();
        vo.input(result,PostHistoryVO.class);
        return vo;
    }

}
