package com.boki.bokiadministrator.service;

import com.boki.bokiadministrator.dao.BokiDataDao;
import com.boki.bokiadministrator.service.inter.BokiDataService;
import com.boki.bokiapi.entity.vo.SummaryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/16
 * @Description:
 */
@Service
@Slf4j
@Transactional
public class BokiDataServiceImpl implements BokiDataService {

    @Autowired
    private BokiDataDao bokiDataDao;

    @Override
    public SummaryVO statistics() {
        //返回4个数据
        List<List<?>> result = bokiDataDao.findSomeData();
        SummaryVO vo = new SummaryVO();
        vo.setUserCount((Integer)result.get(0).get(0));
        vo.setPostCount((Integer)result.get(1).get(0));
        vo.setDeletedCount((Integer)result.get(2).get(0));
        vo.setMonthlyPosting((Integer)result.get(3).get(0));
        Float averagePost = vo.getPostCount().floatValue()/vo.getUserCount().floatValue();
        vo.setAveragePost(averagePost);
        return vo;
    }
}
