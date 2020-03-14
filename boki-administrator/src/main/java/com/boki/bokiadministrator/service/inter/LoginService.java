package com.boki.bokiadministrator.service.inter;

import com.boki.bokiapi.entity.dto.request.UserLoginDTO;
import com.boki.bokiapi.entity.vo.UserInfoVO;

public interface LoginService {

    /**
     * 登陆验证
     * @param userLoginDTO
     * @return
     */
    UserInfoVO findByMailAndPwd(UserLoginDTO userLoginDTO);


}
