package com.boki.bokiadministrator.service;

import com.boki.bokiadministrator.bean.DBSourceSelectBean;
import com.boki.bokiadministrator.dao.UserManageDao;
import com.boki.bokiadministrator.service.inter.UserManageService;
import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.dto.UserBaseDTO;
import com.boki.bokiapi.entity.dto.UserDTO;
import com.boki.bokiapi.entity.dto.request.BanUserDTO;
import com.boki.bokiapi.entity.dto.request.UserHistoryDTO;
import com.boki.bokiapi.entity.dto.request.UserRoleChangeDTO;
import com.boki.bokiapi.entity.dto.request.UserSelectDTO;
import com.boki.bokiapi.entity.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Service
@Slf4j
@Transactional
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserManageDao userManageDao;

    @Autowired
    private DBSourceSelectBean dbSource;

    @Override
    public DataWithTotal getUserList(UserSelectDTO dto) {
        if (dto.getMinLevel() != null){
            dto.setMinExp(dbSource.getMinExp(dto.getMinLevel()));
        }
        if (dto.getMaxLevel() != null) {
            dto.setMaxExp(dbSource.getMaxExp(dto.getMaxLevel()));
        }
        dto.setStart((dto.getPage()-1)*15).setEnd(15);
        List<List<?>> result = userManageDao.findUsers(dto);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result, UserBaseVO.class);
        if (vo.getList().size() > 0) {
            for (int i = 0; i < vo.getList().size(); i++) {
                //经验值转等级
                Long exp = ((UserBaseDTO) result.get(1).get(i)).getExp();
                ((UserBaseVO) vo.getList().get(i)).setLevel(dbSource.getLv(exp));
                //信誉值转勋章id
                Integer creditDegree = ((UserBaseDTO) result.get(1).get(i)).getCreditDegree();
                ((UserBaseVO) vo.getList().get(i)).setHonorId(dbSource.getHonorId(creditDegree));
            }
        }
        return vo;
    }

    @Override
    public UserInfoVO getUserInfo(String idOrMail) {
        UserDTO dto = userManageDao.findUser(idOrMail);
        if (dto == null)return null;
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(dto,vo);
        vo.setLevel(dbSource.getLv(vo.getExp()));
        vo.setHonorId(dbSource.getHonorId(vo.getCreditDegree()));
        vo.setRole(dbSource.getRoleById(vo.getRoleId()));
        return vo;
    }

    @Override
    public Integer roleChange(UserRoleChangeDTO dto) {
        return userManageDao.modifyUserRole(dto);
    }

    @Override
    public Integer banUser(BanUserDTO dto) {
        //解封时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,dto.getDays());
        dto.setBanUntil(sdf.format(calendar.getTime()));
        return userManageDao.updateToBanUser(dto);

    }

    @Override
    public Integer releaseUser(Long userId) {
        return userManageDao.updateToReleaseUser(userId);
    }

    @Override
    public DataWithTotal postHistory(UserHistoryDTO dto) {
        dto.setStart((dto.getPage()-1)*15).setEnd(15);
        List<List<?>> result = userManageDao.findPostHistory(dto);
        for (Object o : result.get(1)){
            PostDTO postDTO = ((PostDTO)o);
            ((PostDTO)o).setType(dbSource.getType(postDTO.getTypeId()));
        }
        DataWithTotal vo = new DataWithTotal();
        vo.input(result, PostHistoryAdminVO.class);
        return vo;
    }

    @Override
    public DataWithTotal replyHistory(UserHistoryDTO dto) {
        dto.setStart((dto.getPage()-1)*15).setEnd(15);
        List<List<?>> result = userManageDao.findReplyHistory(dto);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result, ReplyHistoryVO.class);
        return vo;
    }
}
