package com.ecommerce_platform.Entity;

import java.time.LocalDateTime;


import org.springframework.context.support.BeanDefinitionDsl.Role;

import com.ecommerce_platform.Enum.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;
@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "Name is required")
	    private String name;

	    @Column(unique = true)
	    @NotBlank(message = "Email is required")
	    private String email;

	    @NotBlank(message = "Password number is required")
	    private String password;

	    @Column(name = "phone_number")
	    @NotBlank(message = "Phone number is required")
	    private  String phoneNumber;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "role", length = 50, nullable = false)
	    private UserRole role;

	    public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}
		
		@Column(name = "created_at")
	    private LocalDateTime createdAt = LocalDateTime.now();
		

		public User() {
			super();
		}

		// ✅ Private Constructor (Used by the Builder)
	    private User(Long id, String name, String email, String password, String phoneNumber, UserRole role, LocalDateTime createdAt) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.phoneNumber = phoneNumber;
	        this.role = role;
	        this.createdAt = createdAt;
	    }

	    // ✅ Manually Defined Builder Method
	    public static UserBuilder builder() {
	        return new UserBuilder();
	    }

	    // ✅ Builder Class for `User`
	    public static class UserBuilder {
	        private Long id;
	        private String name;
	        private String email;
	        private String password;
	        private String phoneNumber;
	        private UserRole role;
	        private LocalDateTime createdAt = LocalDateTime.now(); // Default time

	        public UserBuilder id(Long id) {
	            this.id = id;
	            return this;
	        }

	        public UserBuilder name(String name) {
	            this.name = name;
	            return this;
	        }

	        public UserBuilder email(String email) {
	            this.email = email;
	            return this;
	        }

	        public UserBuilder password(String password) {
	            this.password = password;
	            return this;
	        }

	        public UserBuilder phoneNumber(String phoneNumber) {
	            this.phoneNumber = phoneNumber;
	            return this;
	        }

	        public UserBuilder role(UserRole role) {
	            this.role = role;
	            return this;
	        }

	        public UserBuilder createdAt(LocalDateTime createdAt) {
	            this.createdAt = createdAt;
	            return this;
	        }

	        public User build() {
	            return new User(id, name, email, password, phoneNumber, role, createdAt);
	        }
	    }


}
