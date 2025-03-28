package com.ecommerce_platform.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce_platform.Config.JwtUtils;
import com.ecommerce_platform.DTO.LoginRequest;
import com.ecommerce_platform.DTO.Response;
import com.ecommerce_platform.DTO.UserDto;
import com.ecommerce_platform.Entity.User;
import com.ecommerce_platform.Enum.UserRole;
import com.ecommerce_platform.Exception.InvalidCredentialsException;
import com.ecommerce_platform.Exception.NotFoundException;
import com.ecommerce_platform.Mapper.EntityDtoMapper;
import com.ecommerce_platform.Repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
//@RequiredArgsConstructor
public class AuthService implements UserService{
	private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EntityDtoMapper entityDtoMapper;
    
    @Autowired
    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, EntityDtoMapper entityDtoMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.entityDtoMapper = entityDtoMapper;
    }


    @Transactional
    public Response registerUser(UserDto registrationRequest) {
        UserRole role = UserRole.USER;

        if (registrationRequest.getRole() != null && registrationRequest.getRole().equalsIgnoreCase("admin")) {
            role = UserRole.ADMIN;
        }

        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phoneNumber(registrationRequest.getPhoneNumber())
                .role(role)
                .build();

        User savedUser = userRepo.save(user);
        System.out.println(savedUser);

        UserDto userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);
        return Response.builder()
                .status(200)
                .message("User Successfully Added")
                .user(userDto)
                .build();
    }



    public Response loginUser(LoginRequest loginRequest) {

        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new NotFoundException("Email not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Password does not match");
        }
        String token = jwtUtils.generateToken(user);

        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("6 Month")
                .role(user.getRole().name())
                .build();
    }

}
