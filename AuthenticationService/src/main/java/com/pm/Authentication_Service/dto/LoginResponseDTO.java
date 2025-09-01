package com.pm.Authentication_Service.dto;



public class LoginResponseDTO {

    private String token;
    private String message;
    private boolean isError;

    // Static factory method for success
    public static LoginResponseDTO success(String token) {
        LoginResponseDTO dto = new LoginResponseDTO();
        dto.token = token;
        dto.isError = false;
        return dto;
    }

    // Static factory method for error
    public static LoginResponseDTO error(String message) {
        LoginResponseDTO dto = new LoginResponseDTO();
        dto.message = message;
        dto.isError = true;
        return dto;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return isError;
    }

    // Private constructor to force use of factory methods
    private LoginResponseDTO() {}
}
