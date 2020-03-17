package com.boki.bokiclient.controller;

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

import javax.servlet.http.HttpSession;
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
    public ResultVO info(HttpSession session){
        UserInfoVO vo = userService.userInfo((Long)session.getAttribute("UID"));
        return vo != null ? RequestResultCode.SUCCESS.getResult().setData(vo) : RequestResultCode.FAIL.getResult();
    }


    /**
     * 用户修改个人信息
     */
    @PostMapping("/modifyInfo")
    public ResultVO modifyInfo(@RequestBody @Valid UserInfoDTO dto, HttpSession session){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(sdf.parse(dto.getBirth()).after(new Date())){
                throw new BusinessException("你tm生日是未来？").setType(RequestResultCode.BIRTH_IN_THE_PATH);
            }
        } catch (ParseException e) {
            throw new BusinessException("日期转换错误"+e.getMessage()).setType(RequestResultCode.ERROR_DATE);
        }
        dto.setId((Long)session.getAttribute("UID"));
        int count = userService.modifyInfo(dto);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() :
                count == 0 ? RequestResultCode.NOTHING_HAS_CHANGED.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 用户头像上传
     */
    @PostMapping("/modifyPhoto")
    public ResultVO modifyPhoto(@RequestParam("url")String url,HttpSession session){
        int count = userService.setUserPhoto((Long)session.getAttribute("UID"),url);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() :
                count == 0 ? RequestResultCode.NOTHING_HAS_CHANGED.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 收藏帖子
     */
    @PostMapping("/collect/{postId}")
    public ResultVO collect(@PathVariable("postId")Long postId,HttpSession session){
        int count = userService.collectPost(postId,(Long)session.getAttribute("UID"));
        return count == 1 ? RequestResultCode.SUCCESS.getResult() :
                count == -1 ? RequestResultCode.COLLECTION_EXIST.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 移除收藏
     */
    @PostMapping("/removeCollection/{postId}")
    public ResultVO removeCollection(@PathVariable("postId")Long postId,HttpSession session){
        int count = userService.removeCollection(postId,(Long)session.getAttribute("UID"));
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 帖子收藏列表
     */
    @GetMapping("/postCollection/{page}")
    public ResultVO postCollection(@PathVariable("page")Integer page, HttpSession session){
        DataWithTotal dwt = userService.findPostCollectionByUid((Long)session.getAttribute("UID"),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 关注他
     */
    @PostMapping("/addFollow/{targetUserId}")
    public ResultVO addFollow(@PathVariable("targetUserId")Long targetUserId,HttpSession session){
        if((long)targetUserId == (long)((Long)session.getAttribute("UID"))){
            return RequestResultCode.FOLLOW_CANNOT_SELF.getResult();
        }
        int count = userService.addFollow((Long)session.getAttribute("UID"),targetUserId);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() :
                count == -1 ? RequestResultCode.FOLLOW_EXIST.getResult() :
                        RequestResultCode.FAIL.getResult();
    }

    /**
     * 取消关注
     */
    @PostMapping("/removeFollow/{targetId}")
    public ResultVO removeFollow(@PathVariable("targetId")Long targetId,HttpSession session){
        int count = userService.removeFollow((Long)session.getAttribute("UID"),targetId);
        return count >= 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 关注列表
     */
    @GetMapping("/followList/{page}")
    public ResultVO followList(@PathVariable("page")Integer page,HttpSession session){
        DataWithTotal dwt = userService.findFollowList((Long)session.getAttribute("UID"),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的粉丝
     */
    @GetMapping("/myFans/{page}")
    public ResultVO myFans(@PathVariable("page")Integer page,HttpSession session){
        DataWithTotal dwt =  userService.findFans((Long)session.getAttribute("UID"),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的发帖记录
     */
    @GetMapping("/postHistory/{page}")
    public ResultVO postHistory(@PathVariable("page")Integer page,HttpSession session){
        DataWithTotal dwt = userService.postHistory((Long)session.getAttribute("UID"),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 我的回复记录
     */
    @GetMapping("/replyHistory/{page}")
    public ResultVO replyHistory(@PathVariable("page")Integer page,HttpSession session){
        DataWithTotal<ReplyHistoryVO> dwt = userService.replyHistory((Long)session.getAttribute("UID"),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


}
