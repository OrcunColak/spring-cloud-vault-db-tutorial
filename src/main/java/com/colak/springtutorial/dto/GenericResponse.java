package com.colak.springtutorial.dto;


import java.time.LocalDateTime;

public record GenericResponse(boolean success, String message, Object data, int code, LocalDateTime timestamp) {
}
