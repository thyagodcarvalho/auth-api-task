package com.moatbuilders.task.domian.user;

public record RegisterDTO(String fullName, String username, String password, UserRole role) {
}
