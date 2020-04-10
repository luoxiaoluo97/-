package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.PostVO;
import com.boki.bokiapi.value.Common;
import com.boki.bokiclient.bean.DBSourceSelectBean;
import com.boki.bokiclient.dao.HomeDao;
import com.boki.bokiclient.service.inter.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: LJF
 * @Date: 2020/2/27
 * @Description:
 */
@Slf4j
@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeDao homeDao;

    @Autowired
    private DBSourceSelectBean dbSource;

    @Override
    public DataWithTotal findPosts(Integer type,Integer page, String titleKey) {
        if(titleKey != null){
            titleKey = Pattern.compile("\\s+").matcher(titleKey).matches() ? Common.EMPTY : titleKey;
        }
        List<List<?>> result = homeDao.findPosts(type,titleKey,(page-1)*20,20);
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
                //楼主的荣誉id
                Integer hostCreditDegree = ((PostDTO)(result.get(1).get(i))).getHostCreditDegree();
                postVO.setHostHonorId(dbSource.getHonorId(hostCreditDegree));
                //最后回复者荣誉id
                Integer lastReplierCreditDegree = ((PostDTO)(result.get(1).get(i))).getLastReplierCreditDegree();
                postVO.setLastReplierHonorId(dbSource.getHonorId(lastReplierCreditDegree));
            }
        }
        return vo;
    }
}
