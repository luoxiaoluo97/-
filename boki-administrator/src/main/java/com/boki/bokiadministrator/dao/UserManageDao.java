package com.boki.bokiadministrator.dao;

import com.boki.bokiapi.entity.dto.UserDTO;
import com.boki.bokiapi.entity.dto.request.BanUserDTO;
import com.boki.bokiapi.entity.dto.request.UserHistoryDTO;
import com.boki.bokiapi.entity.dto.request.UserRoleChangeDTO;
import com.boki.bokiapi.entity.dto.request.UserSelectDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserManageDao {

    /**
     * 用户列表，按页获取
     * @param dto
     * @return
     */
    List<List<?>> findUsers(UserSelectDTO dto);

    /**
     * 用户信息
     */
    UserDTO findUser(@Param("idOrMail")String idOrMail);

    /**
     * 调整角色
     */
    Integer modifyUserRole(UserRoleChangeDTO dto);

    /**
     * 封号
     */
    Integer updateToBanUser(BanUserDTO dto);

    /**
     * 解封
     */
    Integer updateToReleaseUser(@Param("userId") Long userId);

    /**
     * 发帖历史
     */
    List<List<?>> findPostHistory(UserHistoryDTO dto);

    /**
     * 回复历史
     */
    List<List<?>> findReplyHistory(UserHistoryDTO dto);
}
