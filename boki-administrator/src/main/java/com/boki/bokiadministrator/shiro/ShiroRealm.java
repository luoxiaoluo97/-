package com.boki.bokiadministrator.shiro;

import com.boki.bokiadministrator.bean.DBSourceSelectBean;
import com.boki.bokiapi.entity.dto.request.UserLoginDTO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiadministrator.service.inter.LoginService;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
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

    @Autowired
    private DBSourceSelectBean dbSource;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer roleId = ((UserInfoVO)principalCollection.getPrimaryPrincipal()).getRoleId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission(dbSource.getRoleById(roleId));
        return info;
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
        }else if(userInfoVO.getRoleId() == 1){
            throw new BusinessException(userInfoVO.getMail()+"无权限").setType(RequestResultCode.NO_AUTHORIZATION);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfoVO,userLoginDTO.getPwd(),"");
        return info;
    }
}
