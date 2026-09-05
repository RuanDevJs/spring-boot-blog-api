package com.ruandevjs.spring_boot.blog_api.exceptions;

import com.ruandevjs.spring_boot.blog_api.exceptions.posts.PostNotFoundException;
import com.ruandevjs.spring_boot.blog_api.exceptions.users.UserFoundException;
import com.ruandevjs.spring_boot.blog_api.exceptions.users.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handlerMethodArgumentException(MethodArgumentNotValidException exception){
        List<ErrorMessageDTO> errorDTOList = new ArrayList<ErrorMessageDTO>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errorDTOList.add(new ErrorMessageDTO(error.getField(), message));
        });

        return ResponseEntity.badRequest().body(errorDTOList);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<Object> handleUserFoundException(UserFoundException exception){
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception){
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDTO);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException exception){
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDTO);
    }
}

