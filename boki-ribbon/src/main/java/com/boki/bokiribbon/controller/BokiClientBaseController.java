package com.boki.bokiribbon.controller;

import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.*;
import com.boki.bokiribbon.service.inter.ClientBaseFeignClient;
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
    private ClientBaseFeignClient clientBaseFeignClient;

    /**
     * 用户端注册
     */
    @PostMapping("/login/register")
    public ResultVO register(@RequestBody UserRegisterDTO dto){
        ResultVO vo = clientBaseFeignClient.register(dto);
        return vo;
    }

    /**
     * 用户端验证码发送
     */
    @GetMapping("/login/sendCheckCode/{mail}")
    public ResultVO sendCheckCode(@PathVariable String mail){
        ResultVO vo = clientBaseFeignClient.sendCheckCode(mail);
        return vo;
    }

    /**
     * 登陆
     */
    @PostMapping(value = "/login")
    public ResultVO doLogin(@RequestBody UserLoginDTO dto,HttpSession session){
        ResultVO vo = clientBaseFeignClient.login(dto);
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
        ResultVO vo = clientBaseFeignClient.updatePwd(dto);
        return vo;
    }

    /**
     * 个人信息
     */
    @GetMapping("/user/info")
    public ResultVO info(){
        ResultVO vo = clientBaseFeignClient.info();
        return vo;
    }

    /**
     * 用户修改个人信息
     */
    @PostMapping("/user/modifyInfo")
    public ResultVO modifyInfo(@RequestBody UserInfoDTO dto){
        ResultVO vo = clientBaseFeignClient.modifyInfo(dto);
        return vo;
    }

    /**
     * 用户头像上传
     */
    @PostMapping("/user/modifyPhoto")
    public ResultVO modifyPhoto(@RequestBody String url){
        ResultVO vo = clientBaseFeignClient.modifyPhoto(url);
        return vo;
    }

    /**
     * 收藏帖子
     */
    @PostMapping("/user/collect/{postId}")
    public ResultVO collect(@PathVariable("postId")Long postId){
        ResultVO vo = clientBaseFeignClient.collect(postId);
        return vo;
    }

    /**
     * 移除收藏
     */
    @PostMapping("/user/removeCollection/{postId}")
    public ResultVO removeCollection(@PathVariable("postId")Long postId){
        ResultVO vo = clientBaseFeignClient.removeCollection(postId);
        return vo;
    }

    /**
     * 帖子收藏列表
     */
    @GetMapping("/user/postCollection")
    public ResultVO postCollection(Integer page){
        ResultVO vo = clientBaseFeignClient.postCollection(page);
        return vo;
    }

    /**
     * 关注他
     */
    @PostMapping("/user/addFollow/{targetUserId}")
    public ResultVO addFollow(@PathVariable("targetUserId")Long targetUserId){
        ResultVO vo = clientBaseFeignClient.addFollow(targetUserId);
        return vo;
    }

    /**
     * 取消关注
     */
    @PostMapping("/user/removeFollow/{targetId}")
    public ResultVO removeFollow(@PathVariable("targetId")Long targetId){
        ResultVO vo = clientBaseFeignClient.removeFollow(targetId);
        return vo;
    }

    /**
     * 关注列表
     */
    @GetMapping("/user/followList")
    public ResultVO followList(Integer page){
        ResultVO vo = clientBaseFeignClient.followList(page);
        return vo;
    }

    /**
     * 我的粉丝
     */
    @GetMapping("/user/myFans")
    public ResultVO myFans(@RequestParam("page")Integer page){
        ResultVO vo = clientBaseFeignClient.myFans(page);
        return vo;
    }

    /**
     * 我的发帖记录
     */
    @GetMapping("/user/postHistory")
    public ResultVO postHistory(Integer page){
        ResultVO vo = clientBaseFeignClient.postHistory(page);
        return vo;
    }

    /**
     * 我的回复记录
     */
    @GetMapping("/user/replyHistory")
    public ResultVO replyHistory(Integer page){
        ResultVO vo = clientBaseFeignClient.replyHistory(page);
        return vo;
    }

    /**
     * 帖子列表
     * @param type 1=所有帖子，2=只看精品
     */
    @GetMapping("")
    public ResultVO index(Integer type, Integer page){
        ResultVO vo = clientBaseFeignClient.index(type,page);
        return vo;
    }

    /**
     * 打开帖子
     */
    @GetMapping("/p/{postId}")
    public ResultVO findPostById(@PathVariable("postId")Long id, Integer page){
        ResultVO vo = clientBaseFeignClient.findPostById(id,page);
        return vo;
    }

    /**
     * 加载楼中楼
     * @param id 楼层id
     */
    @GetMapping("/p/reply/open")
    public ResultVO findStoreyReply(Long id,Integer page){
        ResultVO vo = clientBaseFeignClient.findStoreyReply(id,page);
        return vo;
    }

    /**
     * 查看他人信息，此为开放性接口
     */
    @GetMapping("/p/UID{id}")
    public ResultVO userInfo(@PathVariable("id") Long id){
        ResultVO vo = clientBaseFeignClient.userInfo(id);
        return vo;
    }

    /**
     * 用户近三天发帖情况
     */
    @GetMapping("/p/user/lastPosts/UID{userId}")
    public ResultVO userLastPosts(@PathVariable("userId")Long userId){
        ResultVO vo = clientBaseFeignClient.userLastPosts(userId);
        return vo;
    }

    /**
     * 发帖
     */
    @PostMapping("/post/send")
    public ResultVO sendPost(@RequestBody PostSendDTO dto){
        ResultVO vo = clientBaseFeignClient.sendPost(dto);
        return vo;
    }

    /**
     * 回帖
     */
    @PostMapping("/post/reply/send")
    public ResultVO sendReply(@RequestBody ReplySendDTO dto){
        ResultVO vo = clientBaseFeignClient.sendReply(dto);
        return vo;
    }

    /**
     * 二级回复
     */
    @PostMapping("/post/storeyReply/send")
    public ResultVO sendStoreyReply(@RequestBody StoreyReplySendDTO dto){
        ResultVO vo = clientBaseFeignClient.sendStoreyReply(dto);
        return vo;
    }

    /**
     * 删帖
     */
    @PostMapping("/post/delete/{id}")
    public ResultVO deletePost(@PathVariable("id") Long id){
        ResultVO vo = clientBaseFeignClient.deletePost(id);
        return vo;
    }

    /**
     * 删楼
     */
    @PostMapping("/post/reply/delete/{id}")
    public ResultVO deleteReply(@PathVariable("id") Long id){
        ResultVO vo = clientBaseFeignClient.deleteReply(id);
        return vo;
    }

    /**
     * 删除楼层回复，即删除楼中楼
     */
    @PostMapping("/post/storeyReply/delete/{id}")
    public ResultVO deleteStoreyReply(@PathVariable("id") Long id){
        ResultVO vo = clientBaseFeignClient.deleteStoreyReply(id);
        return vo;
    }

    /**
     * 举报帖子
     */
    @PostMapping("/post/report")
    public ResultVO reportPost(@RequestBody ReportSendDTO dto){
        ResultVO vo = clientBaseFeignClient.reportPost(dto);
        return vo;
    }

    /**
     * 举报楼层
     */
    @PostMapping("/post/reply/report")
    public ResultVO reportReply(@RequestBody ReportSendDTO dto){
        ResultVO vo = clientBaseFeignClient.reportReply(dto);
        return vo;
    }

    /**
     * 举报楼中楼
     */
    @PostMapping("/post/storyReply/report")
    public ResultVO reportStoryReply(@RequestBody ReportSendDTO dto){
        ResultVO vo = clientBaseFeignClient.reportStoryReply(dto);
        return vo;
    }

    /**
     * 打开私信，发起私信
     */
    @PostMapping("/whisper/open/{targetUserId}")
    public ResultVO openWhisper(@PathVariable Long targetUserId){
        ResultVO vo = clientBaseFeignClient.openWhisper(targetUserId);
        return vo;
    }

    /**
     * 发送私信
     */
    @PostMapping("/whisper/send")
    public ResultVO sendWhisper(@RequestBody WhisperSendDTO dto){
        ResultVO vo = clientBaseFeignClient.sendWhisper(dto);
        return vo;
    }

    /**
     * 私信列表
     */
    @GetMapping("/whisper/list")
    public ResultVO whisperList(Integer page){
        ResultVO vo = clientBaseFeignClient.whisperList(page);
        return vo;
    }

    /**
     * 从列表中移除私信
     */
    @PostMapping("/whisper/remove/{id}")
    public ResultVO removeWhisper(@PathVariable Integer id){
        ResultVO vo = clientBaseFeignClient.removeWhisper(id);
        return vo;
    }

    /**
     * 拉黑
     */
    @PostMapping("/whisper/addBlacklist/{targetUserId}")
    public ResultVO addBlacklist(@PathVariable Long targetUserId){
        ResultVO vo = clientBaseFeignClient.addBlacklist(targetUserId);
        return vo;
    }

    /**
     * 黑名单列表
     */
    @GetMapping("/whisper/blacklist")
    public ResultVO blacklist(Integer page){
        ResultVO vo = clientBaseFeignClient.blacklist(page);
        return vo;
    }

    /**
     * 移除黑名单
     */
    @PostMapping("/whisper/removeBlacklist/{blacklistId}")
    public ResultVO removeBlacklist(@PathVariable Integer blacklistId){
        ResultVO vo = clientBaseFeignClient.removeBlacklist(blacklistId);
        return vo;
    }

    /**
     * 获取通知数量
     */
    @GetMapping("/notice/count")
    public ResultVO noticeCount(){
        ResultVO vo = clientBaseFeignClient.noticeCount();
        return vo;
    }

    /**
     * 通知列表
     */
    @GetMapping("/notice/list")
    public ResultVO noticeList(Integer page){
        ResultVO vo = clientBaseFeignClient.noticeList(page);
        return vo;
    }

    /**
     * 移除单个通知
     */
    @PostMapping("/notice/remove/{id}")
    public ResultVO removeNotice(@PathVariable Integer id){
        ResultVO vo = clientBaseFeignClient.removeNotice(id);
        return vo;
    }

    /**
     * 清空通知列表
     */
    @PostMapping("/notice/clear")
    public ResultVO clearNotice(){
        ResultVO vo = clientBaseFeignClient.clearNotice();
        return vo;
    }
}
