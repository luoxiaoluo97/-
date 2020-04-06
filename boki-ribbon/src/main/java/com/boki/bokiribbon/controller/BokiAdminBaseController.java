package com.boki.bokiribbon.controller;

import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.*;
import com.boki.bokiribbon.service.inter.AdminBaseFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LJF
 * @Date: 2020/3/21
 * @Description:
 */
@RestController
@RequestMapping("/admin")
public class BokiAdminBaseController {

    @Autowired
    private AdminBaseFeignClient adminBaseFeignClient;


    /**
     * 数据总览：用户数，帖子数，管理员删帖数，用户人均发帖，近一个月日均发帖量，
     */
    @GetMapping("/statistics")
    public ResultVO statistics(){
        ResultVO vo = adminBaseFeignClient.statistics();
        return vo;
    }

    /**
     * 帖子举报待审核列表 type = "post"、"reply"、"storeyReply"
     */
    @GetMapping("/post/report/{type}")
    public ResultVO reportList(@PathVariable String type,Integer page){
        ResultVO vo = adminBaseFeignClient.reportList(type,page);
        return vo;
    }

    /**
     * 驳回举报请求
     */
    @PostMapping("/post/report/reject")
    public ResultVO reject(@RequestBody ReportJudgeDTO dto){
        ResultVO vo = adminBaseFeignClient.reject(dto);
        return vo;
    }

    /**
     * 举报成功，同意删帖
     */
    @PostMapping("/post/report/pass")
    public ResultVO pass(@RequestBody ReportJudgeDTO dto){
        ResultVO vo = adminBaseFeignClient.pass(dto);
        return vo;
    }


    /**
     * 加精或降级
     */
    @PostMapping("/post/upgrade")
    public ResultVO postUpgrade(@RequestBody PostUpgradeDTO dto){
        ResultVO vo = adminBaseFeignClient.postUpgrade(dto);
        return vo;
    }

    /**
     * 置顶
     */
    @PostMapping("/post/setTop")
    public ResultVO postSetTop(@RequestBody PostSetTopDTO dto){
        ResultVO vo = adminBaseFeignClient.postSetTop(dto);
        return vo;
    }

    /**
     * 取消置顶
     */
    @PostMapping("/post/cancelTop/{postId}")
    public ResultVO cancelTop(@PathVariable Long postId){
        ResultVO vo = adminBaseFeignClient.cancelTop(postId);
        return vo;
    }

    /**
     * 普通用户列表,查询所有,mail或userName模糊查询，是否禁封查询，等级范围查询，信誉范围查询
     */
    @GetMapping("/user/list")
    public ResultVO userList(String elem,
                             Integer maxLevel,
                             Integer minLevel,
                             Integer maxCreditDegree,
                             Integer minCreditDegree,
                             String isBanned,
                             Integer page){
        ResultVO vo = adminBaseFeignClient.userList(elem,maxLevel,minLevel,maxCreditDegree,minCreditDegree,isBanned,page);
        return vo;
    }

    /**
     * 用户个人信息,id或mail精准查询
     */
    @GetMapping("/user/info")
    public ResultVO userInfo(String idOrMail){
        ResultVO vo = adminBaseFeignClient.userInfo(idOrMail);
        return vo;
    }

    /**
     * 晋升或贬职，站长专属权力
     */
    @PostMapping("/user/role/change")
    public ResultVO roleChange(@RequestBody UserRoleChangeDTO dto){
        ResultVO vo = adminBaseFeignClient.roleChange(dto);
        return vo;
    }

    /**
     * 封号，即小黑屋，只能对一般水友生效
     */
    @PostMapping("/user/ban")
    public ResultVO banUser(@RequestBody BanUserDTO dto){
        ResultVO vo = adminBaseFeignClient.banUser(dto);
        return vo;
    }

    /**
     * 解封
     */
    @PostMapping("/user/release/{userId}")
    public ResultVO releaseUser(@PathVariable Long userId){
        ResultVO vo = adminBaseFeignClient.releaseUser(userId);
        return vo;
    }

    /**
     * 用户发帖记录
     * @param mode 1 查看所有，2仅看管理员删回复
     */
    @GetMapping("/user/post/history")
    public ResultVO postHistory(Long userId,Integer page,Integer mode){
        ResultVO vo = adminBaseFeignClient.postHistory(userId,page,mode);
        return vo;
    }

    /**
     * 用户回复记录
     * @param mode 1 查看所有，2仅看管理员删回复
     */
    @GetMapping("/user/reply/history")
    public ResultVO replyHistory(Long userId,Integer page,Integer mode){
        ResultVO vo = adminBaseFeignClient.replyHistory(userId,page,mode);
        return vo;
    }

//    /**
//     * 上传支持
//     * @return 返回图片url
//     */
//    @PostMapping("/images/upload")
//    public ResultVO uploadFile(@RequestParam("fileName") MultipartFile file, HttpServletRequest request){
//        ResultVO vo = adminBaseFeignClient.uploadFile(file);
//        return vo;
//    }


}
