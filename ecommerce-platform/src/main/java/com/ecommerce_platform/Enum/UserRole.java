package com.ecommerce_platform.Enum;

public enum UserRole {
	ADMIN, USER;

	public boolean equalsIgnoreCase(String string) {
        if (string == null) {
            return false;
        }
        return this.name().equalsIgnoreCase(string);
    }
}
