package com.ecommerce_platform.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int status;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    private String token;
    private String role;
    private String  expirationTime;

    private int totalPage;
    private long totalElement;

    private UserDto user;
    private List<UserDto> userList;
    
 public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<UserDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}

	// ✅ Custom Builder Method
    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    // ✅ Custom Builder Class
    public static class ResponseBuilder {
        private int status;
        private String message;
        private String token;
        private String role;
        private String expirationTime;
        private int totalPage;
        private long totalElement;
        private UserDto user;
        private List<UserDto> userList;

        public ResponseBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public ResponseBuilder role(String role) {
            this.role = role;
            return this;
        }

        public ResponseBuilder expirationTime(String expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public ResponseBuilder totalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public ResponseBuilder totalElement(long totalElement) {
            this.totalElement = totalElement;
            return this;
        }

        public ResponseBuilder user(UserDto user) {
            this.user = user;
            return this;
        }

        public ResponseBuilder userList(List<UserDto> userList) {
            this.userList = userList;
            return this;
        }

        public Response build() {
            Response response = new Response();
            response.status = this.status;
            response.message = this.message;
            response.token = this.token;
            response.role = this.role;
            response.expirationTime = this.expirationTime;
            response.totalPage = this.totalPage;
            response.totalElement = this.totalElement;
            response.user = this.user;
            response.userList = this.userList;
            return response;
        }
    }

   
}