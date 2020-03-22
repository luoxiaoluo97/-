package com.boki.bokiribbon.service;

import com.boki.bokiribbon.entity.RequestResultCode;
import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.*;
import com.boki.bokiribbon.service.inter.AdminBaseFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: LJF
 * @Date: 2020/3/21
 * @Description:
 */
@Component
public class AdminBaseControllerFallbackFactory implements FallbackFactory<AdminBaseFeignClient> {

    /**
     * 别看了，都是server error
     * @param throwable
     * @return
     */
    @Override
    public AdminBaseFeignClient create(Throwable throwable) {
        return new AdminBaseFeignClient() {

            @Override
            public ResultVO login(UserLoginDTO user) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO statistics() {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO reportList(String type, Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO reject(ReportJudgeDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO pass(ReportJudgeDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO postList(Integer type, Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO findPostById(Long id, Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO findStoreyReply(Long id,Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO postUpgrade(PostUpgradeDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO postSetTop(PostSetTopDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO cancelTop(Long postId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO userList(String elem, Integer maxLevel, Integer minLevel, Integer maxCreditDegree, Integer minCreditDegree, String isBanned, Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO userInfo(String idOrMail) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO roleChange(UserRoleChangeDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO banUser(BanUserDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO releaseUser(Long userId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO postHistory(Long userId, Integer page, Integer mode) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO replyHistory(Long userId, Integer page, Integer mode) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO uploadFile(MultipartFile file) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }


        };
    }
}
