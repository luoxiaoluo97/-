package com.boki.bokiclient.service.inter;

import com.boki.bokiapi.entity.dto.UserLoginDTO;
import com.boki.bokiapi.entity.vo.UserInfoVO;

public interface LoginService {

    public UserInfoVO findByMailAndPwd(UserLoginDTO userLoginDTO);

    public int insertUser(UserLoginDTO userLoginDTO);
}
