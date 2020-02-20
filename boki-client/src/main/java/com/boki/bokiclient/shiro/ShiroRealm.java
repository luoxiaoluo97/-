package com.boki.bokiclient.shiro;

import com.boki.bokiapi.entity.dto.UserLoginDTO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiclient.service.inter.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @time: 2020/2/17
 * @author: LJF
 * @description:
 */

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setMail(token.getPrincipal().toString())
                .setPwd(new String((char[])token.getCredentials()));
        UserInfoVO userInfoVO = loginService.findByMailAndPwd(userLoginDTO);
        if(userInfoVO == null){
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfoVO,userLoginDTO.getPwd(),"");
        return info;
    }
}
