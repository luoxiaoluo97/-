package com.boki.bokiribbon.controller;

import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.UserInfoDTO;
import com.boki.bokiribbon.entity.request.UserLoginDTO;
import com.boki.bokiribbon.entity.request.UserRegisterDTO;
import com.boki.bokiribbon.entity.request.UserUpdatePwdDTO;
import com.boki.bokiribbon.service.inter.ClientBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: LJF
 * @Date: 2020/3/19
 * @Description: 用户端接口
 */
@RestController
@RequestMapping("/cli")
public class BokiClientBaseController {

    @Autowired
    private ClientBaseController clientBaseController;

    /**
     * 用户端注册
     */
    @PostMapping("/login/register")
    public ResultVO register(@RequestBody UserRegisterDTO dto){
        ResultVO vo = clientBaseController.register(dto);
        return vo;
    }

    /**
     * 用户端验证码发送
     */
    @GetMapping("/login/sendCheckCode/{mail}")
    public ResultVO sendCheckCode(@PathVariable String mail){
        ResultVO vo = clientBaseController.sendCheckCode(mail);
        return vo;
    }

    /**
     * 登陆
     */
    @PostMapping(value = "/login")
    public ResultVO doLogin(@RequestBody UserLoginDTO dto,HttpSession session){
        ResultVO vo = clientBaseController.login(dto);
        if (vo.getCode() != 50000 && vo.getCode() != 10001 && vo.getCode() != 9999) {
            session.setAttribute("UID", ((Map) vo.getData()).get("id"));
            session.setAttribute("userName", ((Map) vo.getData()).get("userName"));
            session.setAttribute("mail", ((Map) vo.getData()).get("mail"));
            session.setAttribute("roleId",((Map) vo.getData()).get("roleId"));
        }
        return vo;
    }

    /**
     * 改密
     */
    @PostMapping("/login/modifyPwd")
    public ResultVO updatePwd(@RequestBody UserUpdatePwdDTO dto){
        ResultVO vo = clientBaseController.updatePwd(dto);
        return vo;
    }

    /**
     * 个人信息
     */
    @GetMapping("/user/info")
    public ResultVO info(){
        ResultVO vo = clientBaseController.info();
        return vo;
    }

    /**
     * 用户修改个人信息
     */
    @PostMapping("/user/modifyInfo")
    public ResultVO modifyInfo(@RequestBody UserInfoDTO dto){
        ResultVO vo = clientBaseController.modifyInfo(dto);
        return vo;
    }

    /**
     * 用户头像上传
     */
    @PostMapping("/user/modifyPhoto")
    public ResultVO modifyPhoto(@RequestBody String url){
        ResultVO vo = clientBaseController.modifyPhoto(url);
        return vo;
    }

    /**
     * 收藏帖子
     */
    @PostMapping("/user/collect/{postId}")
    public ResultVO collect(@PathVariable("postId")Long postId){
        ResultVO vo = clientBaseController.collect(postId);
        return vo;
    }

    /**
     * 移除收藏
     */
    @PostMapping("/user/removeCollection/{postId}")
    public ResultVO removeCollection(@PathVariable("postId")Long postId){
        ResultVO vo = clientBaseController.removeCollection(postId);
        return vo;
    }

    /**
     * 帖子收藏列表
     */
    @GetMapping("/user/postCollection")
    public ResultVO postCollection(Integer page){
        ResultVO vo = clientBaseController.postCollection(page);
        return vo;
    }

    /**
     * 关注他
     */
    @PostMapping("/user/addFollow/{targetUserId}")
    public ResultVO addFollow(@PathVariable("targetUserId")Long targetUserId){
        ResultVO vo = clientBaseController.addFollow(targetUserId);
        return vo;
    }

    /**
     * 取消关注
     */
    @PostMapping("/user/removeFollow/{targetId}")
    public ResultVO removeFollow(@PathVariable("targetId")Long targetId){
        ResultVO vo = clientBaseController.removeFollow(targetId);
        return vo;
    }

    /**
     * 关注列表
     */
    @GetMapping("/user/followList")
    public ResultVO followList(Integer page){
        ResultVO vo = clientBaseController.followList(page);
        return vo;
    }

    /**
     * 我的粉丝
     */
    @GetMapping("/user/myFans")
    public ResultVO myFans(@RequestParam("page")Integer page){
        ResultVO vo = clientBaseController.myFans(page);
        return vo;
    }

    /**
     * 我的发帖记录
     */
    @GetMapping("/user/postHistory")
    public ResultVO postHistory(Integer page){
        ResultVO vo = clientBaseController.postHistory(page);
        return vo;
    }

    /**
     * 我的回复记录
     */
    @GetMapping("/user/replyHistory")
    public ResultVO replyHistory(Integer page){
        ResultVO vo = clientBaseController.replyHistory(page);
        return vo;
    }

    /**
     * 帖子列表
     * @param type 1=所有帖子，2=只看精品
     */
    @GetMapping("")
    public ResultVO index(Integer type, Integer page){
        ResultVO vo = clientBaseController.index(type,page);
        return vo;
    }

    /**
     * 打开帖子
     */
    @GetMapping("/p/{postId}")
    public ResultVO findPostById(@PathVariable("postId")Long id, Integer page){
        ResultVO vo = clientBaseController.findPostById(id,page);
        return vo;
    }

    /**
     * 加载楼中楼
     * @param id 楼层id
     */
    @GetMapping("/p/reply/open")
    public ResultVO findStoreyReply(Long id,Integer page){
        ResultVO vo = clientBaseController.findStoreyReply(id,page);
        return vo;
    }

    /**
     * 查看他人信息，此为开放性接口
     */
    @GetMapping("/p/UID{id}")
    public ResultVO userInfo(@PathVariable("id") Long id){
        ResultVO vo = clientBaseController.userInfo(id);
        return vo;
    }

    /**
     * 用户近三天发帖情况
     */
    @GetMapping("/p/user/lastPosts/UID{userId}")
    public ResultVO userLastPosts(@PathVariable("userId")Long userId){
        ResultVO vo = clientBaseController.userLastPosts(userId);
        return vo;
    }
}
