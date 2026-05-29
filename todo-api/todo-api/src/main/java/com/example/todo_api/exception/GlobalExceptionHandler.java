package com.example.todo_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

// captura exceções de TODOS os controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    // captura TaskNotFoundException
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleNotFound(
            TaskNotFoundException ex) {
        return Map.of("erro", ex.getMessage());
    }

    // captura erros de validação (@NotBlank etc)
    @ExceptionHandler(
      org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidation(
      org.springframework.web.bind.MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .findFirst()
            .orElse("Dados inválidos");
        return Map.of("erro", msg);
    }
}