package com.boki.bokiclient.controller;

import com.alibaba.fastjson.JSON;
import com.boki.bokiapi.entity.dto.request.UserInfoDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ReplyHistoryVO;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiclient.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @GetMapping("/info")
    public ResultVO info(HttpServletRequest request){
        System.out.println(request.getHeader("UID"));
        UserInfoVO vo = userService.userInfo(Long.parseLong(request.getHeader("UID")));
        return vo != null ? RequestResultCode.SUCCESS.getResult().setData(vo) : RequestResultCode.FAIL.getResult();
    }


    /**
     * 用户修改个人信息
     */
    @PostMapping("/modifyInfo")
    public ResultVO modifyInfo(@RequestBody @Valid UserInfoDTO dto, HttpServletRequest request){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(sdf.parse(dto.getBirth()).after(new Date())){
                throw new BusinessException("你tm生日是未来？").setType(RequestResultCode.BIRTH_IN_THE_PATH);
            }
        } catch (ParseException e) {
            throw new BusinessException("日期转换错误"+e.getMessage()).setType(RequestResultCode.ERROR_DATE);
        }
        dto.setId(Long.parseLong(request.getHeader("UID")));
        int count = userService.modifyInfo(dto);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() :
                count == 0 ? RequestResultCode.NOTHING_HAS_CHANGED.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 用户头像上传
     */
    @PostMapping("/modifyPhoto")
    public ResultVO modifyPhoto(@RequestBody String url,HttpServletRequest request){
        url = JSON.parseObject(url).get("url").toString();
        int count = userService.setUserPhoto(Long.parseLong(request.getHeader("UID")),url);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() :
                count == 0 ? RequestResultCode.NOTHING_HAS_CHANGED.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 收藏帖子
     */
    @PostMapping("/collect/{postId}")
    public ResultVO collect(@PathVariable("postId")Long postId,HttpServletRequest request){
        int count = userService.collectPost(postId,Long.parseLong(request.getHeader("UID")));
        return count == 1 ? RequestResultCode.SUCCESS.getResult() :
                count == -1 ? RequestResultCode.COLLECTION_EXIST.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 移除收藏
     */
    @PostMapping("/removeCollection/{postId}")
    public ResultVO removeCollection(@PathVariable("postId")Long postId,HttpServletRequest request){
        int count = userService.removeCollection(postId,Long.parseLong(request.getHeader("UID")));
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 帖子收藏列表
     */
    @GetMapping("/postCollection")
    public ResultVO postCollection(Integer page,HttpServletRequest request){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = userService.findPostCollectionByUid(Long.parseLong(request.getHeader("UID")),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 关注他
     */
    @PostMapping("/addFollow/{targetUserId}")
    public ResultVO addFollow(@PathVariable("targetUserId")Long targetUserId,HttpServletRequest request){
        if(targetUserId.longValue() == Long.parseLong(request.getHeader("UID"))){
            return RequestResultCode.FOLLOW_CANNOT_SELF.getResult();
        }
        int count = userService.addFollow(Long.parseLong(request.getHeader("UID")),targetUserId);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() :
                count == -1 ? RequestResultCode.FOLLOW_EXIST.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 取消关注
     */
    @PostMapping("/removeFollow/{targetId}")
    public ResultVO removeFollow(@PathVariable("targetId")Long targetId,HttpServletRequest request){
        int count = userService.removeFollow(Long.parseLong(request.getHeader("UID")),targetId);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 关注列表
     */
    @GetMapping("/followList")
    public ResultVO followList(Integer page,HttpServletRequest request){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = userService.findFollowList(Long.parseLong(request.getHeader("UID")),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的粉丝
     */
    @GetMapping("/myFans")
    public ResultVO myFans(Integer page, HttpServletRequest request){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt =  userService.findFans(Long.parseLong(request.getHeader("UID")),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的发帖记录
     */
    @GetMapping("/postHistory")
    public ResultVO postHistory(Integer page,HttpServletRequest request){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = userService.postHistory(Long.parseLong(request.getHeader("UID")),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的回复记录
     */
    @GetMapping("/replyHistory")
    public ResultVO replyHistory(Integer page,HttpServletRequest request){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal<ReplyHistoryVO> dwt = userService.replyHistory(Long.parseLong(request.getHeader("UID")),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


}
