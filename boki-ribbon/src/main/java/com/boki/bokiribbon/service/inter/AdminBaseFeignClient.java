package com.boki.bokiribbon.service.inter;


import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.*;
import com.boki.bokiribbon.service.AdminBaseControllerFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(value = "boki-admin",fallbackFactory = AdminBaseControllerFallbackFactory.class)
public interface AdminBaseFeignClient {

    @PostMapping(value = "/login")
    ResultVO login(@RequestBody UserLoginDTO user);

    @GetMapping("/statistics")
    ResultVO statistics();

    @GetMapping("/post/report/{type}")
    ResultVO reportList(@PathVariable("type") String type, @RequestParam("page") Integer page);

    @PostMapping("/post/report/reject")
    ResultVO reject(@RequestBody ReportJudgeDTO dto);

    @PostMapping("/post/report/pass")
    ResultVO pass(@RequestBody ReportJudgeDTO dto);

    @GetMapping("/post/list")
    ResultVO postList(@RequestParam("type")Integer type,@RequestParam("page") Integer page);

    @GetMapping("/post/open")
    ResultVO findPostById(@RequestParam("id")Long id,@RequestParam("page")Integer page);

    @GetMapping("/post/reply/open")
    ResultVO findStoreyReply(@RequestParam("id")Long id,@RequestParam("page")Integer page);

    @PostMapping("/post/upgrade")
    ResultVO postUpgrade(@RequestBody PostUpgradeDTO dto);

    @PostMapping("/post/setTop")
    ResultVO postSetTop(@RequestBody PostSetTopDTO dto);

    @PostMapping("/post/cancelTop/{postId}")
    ResultVO cancelTop(@PathVariable("postId") Long postId);

    @GetMapping("/user/list")
    ResultVO userList(@RequestParam("elem")String elem,
                      @RequestParam("maxLevel")Integer maxLevel,
                      @RequestParam("minLevel")Integer minLevel,
                      @RequestParam("maxCreditDegree")Integer maxCreditDegree,
                      @RequestParam("minCreditDegree")Integer minCreditDegree,
                      @RequestParam("isBanned")String isBanned,
                      @RequestParam("page")Integer page);

    @GetMapping("/user/info")
    ResultVO userInfo(@RequestParam("idOrMail")String idOrMail);

    @PostMapping("/user/role/change")
    ResultVO roleChange(@RequestBody UserRoleChangeDTO dto);

    @PostMapping("/user/ban")
    ResultVO banUser(@RequestBody BanUserDTO dto);

    @PostMapping("/user/release/{userId}")
    ResultVO releaseUser(@PathVariable("userId") Long userId);

    @GetMapping("/user/post/history")
    ResultVO postHistory(@RequestParam("userId") Long userId,@RequestParam("page")Integer page,@RequestParam("mode")Integer mode);

    @GetMapping("/user/reply/history")
    ResultVO replyHistory(@RequestParam("userId")Long userId,@RequestParam("page")Integer page,@RequestParam("mode")Integer mode);

    @PostMapping("/images/upload")
    ResultVO uploadFile(@RequestParam("fileName") MultipartFile fileName);
}
