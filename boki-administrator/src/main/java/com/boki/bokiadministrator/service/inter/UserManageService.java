package com.boki.bokiadministrator.service.inter;

import com.boki.bokiapi.entity.dto.request.BanUserDTO;
import com.boki.bokiapi.entity.dto.request.UserHistoryDTO;
import com.boki.bokiapi.entity.dto.request.UserRoleChangeDTO;
import com.boki.bokiapi.entity.dto.request.UserSelectDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.UserInfoVO;

public interface UserManageService {

    /**
     * 用户列表
     * @param dto
     * @return
     */
    DataWithTotal getUserList(UserSelectDTO dto);

    /**
     * 用户信息，id或userName精准查询
     */
    UserInfoVO getUserInfo(String idOrName);

    /**
     * 晋升或贬职，不适用站长
     */
    Integer roleChange(UserRoleChangeDTO dto);

    /**
     * 禁封水友
     */
    Integer banUser(BanUserDTO dto);

    /**
     * 解封
     */
    Integer releaseUser(Long userId);

    /**
     * 发帖记录
     */
    DataWithTotal postHistory(UserHistoryDTO dto);


    /**
     * 回复记录
     */
    DataWithTotal replyHistory(UserHistoryDTO dto);
}
