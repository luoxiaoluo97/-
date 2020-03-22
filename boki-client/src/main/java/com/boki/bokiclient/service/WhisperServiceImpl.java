package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.request.WhisperSendDTO;
import com.boki.bokiapi.entity.po.BlacklistPO;
import com.boki.bokiapi.entity.po.WhisperDetailPO;
import com.boki.bokiapi.entity.po.WhisperPO;
import com.boki.bokiapi.entity.vo.*;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
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
        if(result.get(0).size() == 0){
            throw new BusinessException().setType(RequestResultCode.FAIL).setInfo("UID"+secondUserId+"不存在，私信打开失败。");
        }
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
        if(whisperDao.checkBlacklist(dto.getTargetUserId(),dto.getUserId()) == 1){
            return -1;
        }
        WhisperDetailPO po = new WhisperDetailPO();
        BeanUtils.copyProperties(dto,po);
        int count  = whisperDao.insertWhisperDetail(po);
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

    @Override
    public Integer addBlacklist(Long userId, Long targetUserId) {
        BlacklistPO po = new BlacklistPO()
                .setUserId(userId)
                .setTargetUserId(targetUserId);
        int count = whisperDao.insertBlacklist(po);
        return count;
    }

    @Override
    public DataWithTotal getBlacklist(Long userId, Integer page) {
        List<List<?>> result = whisperDao.getBlacklistByUid(userId,(page-1)*15,15);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result, BlacklistVO.class);
        return vo;
    }

    @Override
    public Integer removeBlacklist(Long userId, Integer blacklistId) {
        BlacklistPO po = new BlacklistPO()
                .setId(blacklistId)
                .setUserId(userId);
        int count = whisperDao.removeBlacklist(po);
        return count;
    }


}
