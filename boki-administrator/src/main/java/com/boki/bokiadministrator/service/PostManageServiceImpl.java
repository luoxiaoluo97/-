package com.boki.bokiadministrator.service;

import com.boki.bokiadministrator.bean.DBSourceSelectBean;
import com.boki.bokiadministrator.dao.PostManageDao;
import com.boki.bokiadministrator.service.inter.PostManageService;
import com.boki.bokiapi.entity.dto.ReportInfoDTO;
import com.boki.bokiapi.entity.dto.request.PostSetTopDTO;
import com.boki.bokiapi.entity.dto.request.PostUpgradeDTO;
import com.boki.bokiapi.entity.dto.request.ReportJudgeDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public Integer postUpgrade(PostUpgradeDTO dto) {
        int count = postManageDao.updatePostType(dto);
        return count;
    }

    @Override
    public Integer postSetTop(PostSetTopDTO dto) {
        //设置取消置顶时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,dto.getTopDays());
        dto.setTopUntil(sdf.format(calendar.getTime()));

        return postManageDao.updateToSetTop(dto);
    }

    @Override
    public Integer postCancelTop(Long postId, Long modifier) {
        return postManageDao.updateToCancelTop(postId,modifier);
    }


}
