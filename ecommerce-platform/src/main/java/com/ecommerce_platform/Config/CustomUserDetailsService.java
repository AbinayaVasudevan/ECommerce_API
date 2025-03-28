
package com.ecommerce_platform.Config;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce_platform.Entity.User;
import com.ecommerce_platform.Exception.NotFoundException;
import com.ecommerce_platform.Repository.UserRepository;

@Service
//@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;
    
    @Autowired
    public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}


	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username)
                .orElseThrow(()-> new NotFoundException("User/ Email Not found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}