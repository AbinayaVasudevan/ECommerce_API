package com.ecommerce_platform.Service;

import com.ecommerce_platform.DTO.LoginRequest;
import com.ecommerce_platform.DTO.Response;
import com.ecommerce_platform.DTO.UserDto;
import com.ecommerce_platform.Entity.User;

public interface UserService {
	Response registerUser(UserDto registrationRequest);
    Response loginUser(LoginRequest loginRequest);
//    Response getAllUsers();
//    User getLoginUser();
//    Response getUserInfoAndOrderHistory();
}
