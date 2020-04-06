package com.boki.bokiribbon.service;


import com.boki.bokiribbon.entity.RequestResultCode;
import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.*;
import com.boki.bokiribbon.service.inter.ClientBaseFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientBaseControllerFallbackFactory implements FallbackFactory<ClientBaseFeignClient> {

    /**
     * 别看了，都是server error
     * @param throwable
     * @return
     */
    @Override
    public ClientBaseFeignClient create(Throwable throwable) {
        return new ClientBaseFeignClient() {

            @Override
            public ResultVO register(UserRegisterDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO sendCheckCode(String mail) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO login(UserLoginDTO user) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO loginJudge() {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO updatePwd(UserUpdatePwdDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO info() {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO modifyInfo(UserInfoDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO modifyPhoto(String url) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO collect(Long postId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO removeCollection(Long postId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO postCollection(Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO addFollow(Long targetUserId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO removeFollow(Long targetId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO followList(Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO myFans(Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO postHistory(Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO replyHistory(Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO index(Integer type, Integer page) {
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
            public ResultVO userInfo(Long id) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO userLastPosts(Long userId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO sendPost(PostSendDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO sendReply(ReplySendDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO sendStoreyReply(StoreyReplySendDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO deletePost(Long id) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO deleteReply(Long id) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO deleteStoreyReply(Long id) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO reportPost(ReportSendDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO reportReply(ReportSendDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO reportStoryReply(ReportSendDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO openWhisper(Long targetUserId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO sendWhisper(WhisperSendDTO dto) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO whisperList(Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO removeWhisper(Integer id) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO addBlacklist(Long targetUserId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO blacklist(Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO removeBlacklist(Integer blacklistId) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO noticeCount() {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO noticeList(Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO removeNotice(Integer id) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }

            @Override
            public ResultVO clearNotice() {
                return RequestResultCode.SERVER_ERROR.getResult();
            }
        };
    }
}
