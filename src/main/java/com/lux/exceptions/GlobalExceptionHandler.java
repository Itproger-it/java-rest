package com.lux.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = ex.getMessage();
        return new ResponseEntity<>(message, status);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = "Ошибка в запросе";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        if (ex instanceof org.hibernate.exception.ConstraintViolationException) {
            String errorMessage = "Ошибка при валидации данных";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else if (ex instanceof javax.validation.ConstraintViolationException) {
            StringBuilder errorMessage = new StringBuilder("Ошибки валидации: ");
            ((javax.validation.ConstraintViolationException) ex).getConstraintViolations().forEach(violation -> {
                errorMessage.append(violation.getMessage()).append("; ");
            });
            if (errorMessage.length() > 1){
                errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else {
            // Обработка других типов ошибок
            String errorMessage = "Произошла внутренняя ошибка сервера";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseBody
//    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
//        String errorMessage = ex.getMessage(); // Получаем сообщение об ошибке
//        if (errorMessage.contains("status")) {
//            errorMessage = "Неправильное значение статуса стажировки. Допустимые значения: 'открыт', 'закрыт', 'отменен'";
//        } else {
//            errorMessage = "Произошла ошибка при выполнении операции с базой данных";
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//    }

    // Обработчик исключений для ConstraintViolationException
//    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
//    public ResponseEntity<?> handleConstraintViolationException2(javax.validation.ConstraintViolationException ex) {
//        StringBuilder errorMessage = new StringBuilder("Ошибки валидации: ");
//        ex.getConstraintViolations().forEach(violation -> {
//            errorMessage.append(violation.getMessage()).append("; ");
//        });
//        return ResponseEntity.badRequest().body(errorMessage.toString());
//    }

}