package com.boki.bokiribbon.service.inter;

import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.UserInfoDTO;
import com.boki.bokiribbon.entity.request.UserLoginDTO;
import com.boki.bokiribbon.entity.request.UserRegisterDTO;
import com.boki.bokiribbon.entity.request.UserUpdatePwdDTO;
import com.boki.bokiribbon.service.ClientBaseControllerFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "boki-client",fallbackFactory = ClientBaseControllerFallbackFactory.class)
public interface ClientBaseController {


    @PostMapping("/login/register")
    ResultVO register(@RequestBody UserRegisterDTO dto);

    @GetMapping("/login/sendCheckCode/{mail}")
    ResultVO sendCheckCode(@PathVariable("mail") String mail);

    @PostMapping("/login")
    ResultVO login(@RequestBody UserLoginDTO user);

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
}
