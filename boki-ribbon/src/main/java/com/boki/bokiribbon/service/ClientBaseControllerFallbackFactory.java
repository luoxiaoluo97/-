package com.boki.bokiribbon.service;


import com.boki.bokiribbon.entity.RequestResultCode;
import com.boki.bokiribbon.entity.ResultVO;
import com.boki.bokiribbon.entity.request.UserInfoDTO;
import com.boki.bokiribbon.entity.request.UserLoginDTO;
import com.boki.bokiribbon.entity.request.UserRegisterDTO;
import com.boki.bokiribbon.entity.request.UserUpdatePwdDTO;
import com.boki.bokiribbon.service.inter.ClientBaseController;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientBaseControllerFallbackFactory implements FallbackFactory<ClientBaseController> {

    @Override
    public ClientBaseController create(Throwable throwable) {
        return new ClientBaseController() {

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
        };
    }
}
