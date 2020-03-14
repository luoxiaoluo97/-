package com.boki.bokiadministrator.controller;

import com.boki.bokiadministrator.service.inter.UserManageService;
import com.boki.bokiapi.entity.dto.request.BanUserDTO;
import com.boki.bokiapi.entity.dto.request.UserHistoryDTO;
import com.boki.bokiapi.entity.dto.request.UserRoleChangeDTO;
import com.boki.bokiapi.entity.dto.request.UserSelectDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author: LJF
 * @Date: 2020/3/13
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserManageController {

    @Autowired
    private UserManageService userManageService;

    /**
     * 普通用户列表,查询所有,mail或userName模糊查询，是否禁封查询，等级范围查询，信誉范围查询
     */
    @PostMapping("/list")
    public ResultVO userList(@RequestBody @Valid UserSelectDTO dto){
        DataWithTotal dwt = userManageService.getUserList(dto);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


    /**
     * 用户个人信息,id或mail精准查询
     */
    @GetMapping("/info/{idOrName}")
    public ResultVO userInfo(@PathVariable String idOrName){
        UserInfoVO vo = userManageService.getUserInfo(idOrName);
        return vo != null ? RequestResultCode.SUCCESS.getResult().setData(vo) : RequestResultCode.FAIL.getResult();
    }

    /**
     * 晋升或贬职，站长专属权力
     */
    @PostMapping("/role/change")
    public ResultVO roleChange(@RequestBody @Valid UserRoleChangeDTO dto, HttpSession session){
        dto.setId((Long)session.getAttribute("UID"));
        int count = userManageService.roleChange(dto);
        return count == 1? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 封号，即小黑屋，只能对一般水友生效
     */
    @PostMapping("/ban")
    public ResultVO banUser(@RequestBody @Valid BanUserDTO dto){
        int count = userManageService.banUser(dto);
        return count == 1? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 解封
     */
    @PostMapping("/release/{userId}")
    public ResultVO releaseUser(@PathVariable Long userId){
        int count = userManageService.releaseUser(userId);
        return count == 1? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }


    /**
     * 用户发帖记录，查看所有，仅看管理员删帖
     */
    @PostMapping("/post/history")
    public ResultVO postHistory(@RequestBody @Valid UserHistoryDTO dto){
        DataWithTotal dwt = userManageService.postHistory(dto);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 用户回复记录，查看所有，仅看管理员删回复
     */
    @PostMapping("/reply/history")
    public ResultVO replyHistory(@RequestBody @Valid UserHistoryDTO dto){
        DataWithTotal dwt = userManageService.replyHistory(dto);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


}
