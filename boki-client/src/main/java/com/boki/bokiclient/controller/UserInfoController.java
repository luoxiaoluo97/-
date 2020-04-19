package com.boki.bokiclient.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.boki.bokiapi.entity.dto.request.UserInfoDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ReplyHistoryVO;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.Common;
import com.boki.bokiclient.security.Token;
import com.boki.bokiclient.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description: 用户信息
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {


    @Autowired
    private UserService userService;


    /**
     * 用户个人信息
     */
    @Token
    @GetMapping("/info")
    public ResultVO info(HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        UserInfoVO vo = userService.userInfo(Long.parseLong(audience.get(0)));
        return vo != null ? RequestResultCode.SUCCESS.getResult().setData(vo) : RequestResultCode.FAIL.getResult();
    }


    /**
     * 用户修改个人信息
     */
    @Token
    @PostMapping("/modifyInfo")
    public ResultVO modifyInfo(@RequestBody @Valid UserInfoDTO dto, HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(sdf.parse(dto.getBirth()).after(new Date())){
                throw new BusinessException().setType(RequestResultCode.BIRTH_IN_THE_PATH);
            }
        } catch (ParseException e) {
            throw new BusinessException("日期转换错误"+e.getMessage()).setType(RequestResultCode.ERROR_DATE);
        }
        dto.setId(Long.parseLong(audience.get(0)));
        int count = userService.modifyInfo(dto);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() :
                count == 0 ? RequestResultCode.NOTHING_HAS_CHANGED.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 用户头像上传
     */
    @Token
    @PostMapping("/modifyPhoto")
    public ResultVO modifyPhoto(@RequestBody String url,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        url = JSON.parseObject(url).get("url").toString();
        int count = userService.setUserPhoto(Long.parseLong(audience.get(0)), url);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() :
                count == 0 ? RequestResultCode.NOTHING_HAS_CHANGED.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 收藏帖子
     */
    @Token
    @PostMapping("/collect/{postId}")
    public ResultVO collect(@PathVariable("postId")Long postId,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        int count = userService.collectPost(postId,Long.parseLong(audience.get(0)));
        return count == 1 ? RequestResultCode.SUCCESS.getResult() :
                count == -1 ? RequestResultCode.COLLECTION_EXIST.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 移除收藏
     * @param postId 这个实际上是id，写错了
     */
    @Token
    @PostMapping("/removeCollection/{postId}")
    public ResultVO removeCollection(@PathVariable("postId")Long postId,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        int count = userService.removeCollection(postId,Long.parseLong(audience.get(0)));
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 帖子收藏列表
     */
    @Token
    @GetMapping("/postCollection")
    public ResultVO postCollection(Integer page,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = userService.findPostCollectionByUid(Long.parseLong(audience.get(0)),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 关注他
     */
    @Token
    @PostMapping("/addFollow/{targetUserId}")
    public ResultVO addFollow(@PathVariable("targetUserId")Long targetUserId,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        if(targetUserId.longValue() == Long.parseLong(audience.get(0))){
            return RequestResultCode.FOLLOW_CANNOT_SELF.getResult();
        }
        int count = userService.addFollow(Long.parseLong(audience.get(0)),targetUserId);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() :
                count == -1 ? RequestResultCode.FOLLOW_EXIST.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 取消关注
     */
    @Token
    @PostMapping("/removeFollow/{targetId}")
    public ResultVO removeFollow(@PathVariable("targetId")Long targetId,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        int count = userService.removeFollow(Long.parseLong(audience.get(0)),targetId);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 关注列表
     */
    @Token
    @GetMapping("/followList")
    public ResultVO followList(Integer page,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = userService.findFollowList(Long.parseLong(audience.get(0)),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的粉丝
     */
    @Token
    @GetMapping("/myFans")
    public ResultVO myFans(Integer page, HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt =  userService.findFans(Long.parseLong(audience.get(0)),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的发帖记录
     */
    @Token
    @GetMapping("/postHistory")
    public ResultVO postHistory(Integer page,Long userId,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        if(userId == null ){
            userId = Long.parseLong(audience.get(0));
        }
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = userService.postHistory(userId,page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的回复记录
     */
    @Token
    @GetMapping("/replyHistory")
    public ResultVO replyHistory(Integer page,Long userId,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        if(userId == null ){
            userId = Long.parseLong(audience.get(0));
        }
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal<ReplyHistoryVO> dwt = userService.replyHistory(userId,page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


}
