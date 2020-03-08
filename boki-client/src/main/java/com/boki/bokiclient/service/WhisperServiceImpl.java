package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.request.WhisperSendDTO;
import com.boki.bokiapi.entity.po.WhisperDetailPO;
import com.boki.bokiapi.entity.po.WhisperPO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.WhisperDetailVO;
import com.boki.bokiapi.entity.vo.WhisperInfoVO;
import com.boki.bokiapi.entity.vo.WhisperVO;
import com.boki.bokiclient.dao.WhisperDao;
import com.boki.bokiclient.service.inter.WhisperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/7
 * @Description:
 */
@Slf4j
@Service
@Transactional
public class WhisperServiceImpl implements WhisperService {

    @Autowired
    private WhisperDao whisperDao;

    /**
     * 创建会话的肯定是甲方，即creator字段为甲方
     * 通常回访问三次数据库，优化空间很大
     */
    @Override
    public WhisperVO openWhisper(Long firstUserId, Long secondUserId) {
        WhisperPO po = new WhisperPO()
                .setFirstUserId(firstUserId)
                .setSecondUserId(secondUserId);
        //不存在则创建
        Integer isExist = whisperDao.insertWhisper(po);
        if( isExist == 0 ){
            //在用户会话列表显示
            whisperDao.updateWhisperOnShow(po);
        }
        //返回会话结果
        List<List<?>> result = whisperDao.findWhisper(po);
        WhisperVO vo = new WhisperVO();
        BeanUtils.copyProperties(result.get(0).get(0),vo);
        if(result.get(1).size() > 0 ) {
            vo.setList(new ArrayList<>());
            for (Object o : result.get(1)) {
                WhisperDetailVO whisperDetailVO = new WhisperDetailVO();
                BeanUtils.copyProperties(o, whisperDetailVO);
                vo.getList().add(whisperDetailVO);
            }
        }
        return vo;
    }

    @Override
    public Integer sendWhisper(WhisperSendDTO dto) {
        WhisperDetailPO po = new WhisperDetailPO();
        BeanUtils.copyProperties(dto,po);
        int count  = whisperDao.insertWhisperDetail(po);
        // TODO 进行消息提醒
        // TODO 拉黑判断
        return count;
    }

    @Override
    public DataWithTotal getWhisperList(Long userId, Integer page) {
        List<List<?>> result = whisperDao.getWhisperList(userId,(page-1)*15,15);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result, WhisperInfoVO.class);
        return vo;
    }

    @Override
    public Integer removeWhisper(Long userId, Integer whisperId) {
        WhisperPO po = new WhisperPO()
                .setId(whisperId)
                .setFirstUserId(userId);    //把它暂时赋予甲，之后动态sql判断是否为甲
        Integer count = whisperDao.removeWhisper(po);
        return count;
    }


}
