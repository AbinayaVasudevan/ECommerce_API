package com.ecommerce_platform.Config;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce_platform.Entity.User;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class AuthUser implements UserDetails {
	private User user;
	private AuthUser(User user) {
        this.user = user;
    }

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static AuthUserBuilder builder() {
        return new AuthUserBuilder();
    }

    public static class AuthUserBuilder {
        private User user;

        public AuthUserBuilder user(User user) {
            this.user = user;
            return this;
        }

        public AuthUser build() {
            return new AuthUser(user);
        }
    }

	
}
