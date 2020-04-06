package com.boki.bokiribbon.service.inter;

import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.*;
import com.boki.bokiribbon.service.ClientBaseControllerFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "boki-client",fallbackFactory = ClientBaseControllerFallbackFactory.class)
public interface ClientBaseFeignClient {


    @PostMapping("/login/register")
    ResultVO register(@RequestBody UserRegisterDTO dto);

    @GetMapping("/login/sendCheckCode/{mail}")
    ResultVO sendCheckCode(@PathVariable("mail") String mail);

    @PostMapping("/login")
    ResultVO login(@RequestBody UserLoginDTO user);

    @GetMapping("/login/judge")
    ResultVO loginJudge();

    @PostMapping("/login/modifyPwd")
    ResultVO updatePwd(@RequestBody UserUpdatePwdDTO dto);

    @GetMapping("/user/info")
    ResultVO info();

    @PostMapping("/user/modifyInfo")
    ResultVO modifyInfo(@RequestBody UserInfoDTO dto);

    @PostMapping("/user/modifyPhoto")
    ResultVO modifyPhoto(@RequestBody String url);

    @PostMapping("/user/collect/{postId}")
    ResultVO collect(@PathVariable("postId")Long postId);

    @PostMapping("/user/removeCollection/{postId}")
    ResultVO removeCollection(@PathVariable("postId")Long postId);

    @GetMapping("/user/postCollection")
    ResultVO postCollection(@RequestParam("page")Integer page);

    @PostMapping("/user/addFollow/{targetUserId}")
    ResultVO addFollow(@PathVariable("targetUserId")Long targetUserId);

    @PostMapping("/user/removeFollow/{targetId}")
    ResultVO removeFollow(@PathVariable("targetId")Long targetId);

    @GetMapping("/user/followList")
    ResultVO followList(@RequestParam("page")Integer page);

    @GetMapping("/user/myFans")
    ResultVO myFans(@RequestParam("page")Integer page);

    @GetMapping("/user/replyHistory")
    ResultVO postHistory(@RequestParam("page")Integer page);

    @GetMapping("/user/replyHistory")
    ResultVO replyHistory(@RequestParam("page")Integer page);

    @GetMapping("/")
    ResultVO index(@RequestParam("type")Integer type,@RequestParam("page") Integer page);

    @GetMapping("/p/{postId}")
    ResultVO findPostById(@PathVariable("postId") Long id, @RequestParam("page") Integer page);

    @GetMapping("/p/reply/open")
    ResultVO findStoreyReply(@RequestParam("id")Long id,@RequestParam("page")Integer page);

    @GetMapping("/p/UID{id}")
    ResultVO userInfo(@PathVariable("id") Long id);

    @GetMapping("/p/user/lastPosts/UID{userId}")
    ResultVO userLastPosts(@PathVariable("userId")Long userId);

    @PostMapping("/post/send")
    ResultVO sendPost(@RequestBody PostSendDTO dto);

    @PostMapping("/post/reply/send")
    ResultVO sendReply(@RequestBody ReplySendDTO dto);

    @PostMapping("/post/storeyReply/send")
    ResultVO sendStoreyReply(@RequestBody StoreyReplySendDTO dto);

    @PostMapping("/post/delete/{id}")
    ResultVO deletePost(@PathVariable("id") Long id);

    @PostMapping("/post/reply/delete/{id}")
    ResultVO deleteReply(@PathVariable("id") Long id);

    @PostMapping("/post/storeyReply/delete/{id}")
    ResultVO deleteStoreyReply(@PathVariable("id") Long id);

    @PostMapping("/post/report")
    ResultVO reportPost(@RequestBody ReportSendDTO dto);

    @PostMapping("/post/reply/report")
    ResultVO reportReply(@RequestBody ReportSendDTO dto);

    @PostMapping("/post/storyReply/report")
    ResultVO reportStoryReply(@RequestBody ReportSendDTO dto);

    @PostMapping("/whisper/open/{targetUserId}")
    ResultVO openWhisper(@PathVariable("targetUserId") Long targetUserId);

    @PostMapping("/whisper/send")
    ResultVO sendWhisper(@RequestBody WhisperSendDTO dto);

    @GetMapping("/whisper/list")
    ResultVO whisperList(@RequestParam("page") Integer page);

    @PostMapping("/whisper/remove/{id}")
    ResultVO removeWhisper(@PathVariable("id") Integer id);

    @PostMapping("/whisper/addBlacklist/{targetUserId}")
    ResultVO addBlacklist(@PathVariable("targetUserId") Long targetUserId);

    @GetMapping("/whisper/blacklist")
    ResultVO blacklist(@RequestParam("page")Integer page);

    @PostMapping("/whisper/removeBlacklist/{blacklistId}")
    ResultVO removeBlacklist(@PathVariable("blacklistId") Integer blacklistId);

    @GetMapping("/notice/count")
    ResultVO noticeCount();

    @GetMapping("/notice/list")
    ResultVO noticeList(@RequestParam("page")Integer page);

    @PostMapping("/notice/remove/{id}")
    ResultVO removeNotice(@PathVariable("id") Integer id);

    @PostMapping("/notice/clear")
    ResultVO clearNotice();
}
