package com.boki.bokiadministrator.controller;

import com.auth0.jwt.JWT;
import com.boki.bokiadministrator.security.Token;
import com.boki.bokiadministrator.service.inter.UserManageService;
import com.boki.bokiapi.entity.dto.request.BanUserDTO;
import com.boki.bokiapi.entity.dto.request.UserHistoryDTO;
import com.boki.bokiapi.entity.dto.request.UserRoleChangeDTO;
import com.boki.bokiapi.entity.dto.request.UserSelectDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 普通用户列表,查询所有,mail或userName模糊查询，是否禁封查询，等级范围查询，信誉范围查询
     */
    @Token
    @GetMapping("/list")
    public ResultVO userList(String elem,
                             Integer maxLevel,
                             Integer minLevel,
                             Integer maxCreditDegree,
                             Integer minCreditDegree,
                             String isBanned,
                             Integer page){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = userManageService.getUserList(
                new UserSelectDTO()
                    .setElem(elem)
                    .setMaxLevel(maxLevel)
                    .setMinLevel(minLevel)
                    .setMaxCreditDegree(maxCreditDegree)
                    .setMinCreditDegree(minCreditDegree)
                    .setIsBanned(isBanned)
                    .setPage(page)
        );
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


    /**
     * 用户个人信息,id或mail精准查询
     */
    @Token
    @GetMapping("/info")
    public ResultVO userInfo(String idOrMail){
        if (Common.EMPTY.equals(idOrMail)){
            throw new BusinessException().setType(RequestResultCode.FAIL);
        }
        UserInfoVO vo = userManageService.getUserInfo(idOrMail);
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }

    /**
     * 晋升或贬职，站长专属权力
     */
    @Token
    @PostMapping("/role/change")
    public ResultVO roleChange(@RequestBody @Valid UserRoleChangeDTO dto, HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        dto.setId(Long.parseLong(audience.get(0)));
        Map tokenCache = redisTemplate.opsForHash().entries(Common.TOKEN + dto.getId());
        if (!"3".equals(tokenCache.get("roleId"))){
            throw new BusinessException("用户" + request.getHeader("userName")+"试图动用站长权限.")
                    .setType(RequestResultCode.NO_AUTHORIZATION);
        }
        int count = userManageService.roleChange(dto);
        return count == 1? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 封号，即小黑屋，只能对一般水友生效
     */
    @Token
    @PostMapping("/ban")
    public ResultVO banUser(@RequestBody @Valid BanUserDTO dto,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        dto.setModifier(Long.parseLong(audience.get(0)));
        int count = userManageService.banUser(dto);
        return count == 1? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 解封
     */
    @Token
    @PostMapping("/release/{userId}")
    public ResultVO releaseUser(@PathVariable Long userId){
        int count = userManageService.releaseUser(userId);
        return count == 1? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }


    /**
     * 用户发帖记录
     * @param mode 1 查看所有，2仅看管理员删回复
     */
    @Token
    @GetMapping("/post/history")
    public ResultVO postHistory(Long userId,Integer page,Integer mode){
        if (userId == null){
            throw new BusinessException().setInfo("用户id不可为空.").setType(RequestResultCode.ERROR_DATE);
        }
        page = page == null ? 1 : page <= 0 ? 1 : page;
        mode = mode == null ? 1 : mode != 2 ? 1 : mode;
        DataWithTotal dwt = userManageService.postHistory(
                new UserHistoryDTO().setUserId(userId).setPage(page).setMode(mode)
        );
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 用户回复记录
     * @param mode 1 查看所有，2仅看管理员删回复
     */
    @Token
    @GetMapping("/reply/history")
    public ResultVO replyHistory(Long userId,Integer page,Integer mode){
        if (userId == null){
            throw new BusinessException().setInfo("用户id不可为空.").setType(RequestResultCode.ERROR_DATE);
        }
        page = page == null ? 1 : page <= 0 ? 1 : page;
        mode = mode == null ? 1 : mode != 2 ? 1 : mode;
        DataWithTotal dwt = userManageService.replyHistory(
                new UserHistoryDTO().setUserId(userId).setPage(page).setMode(mode)
        );
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


}
