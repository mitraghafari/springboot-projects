package com.example.viewWithSecurity.controller;

import com.example.viewWithSecurity.exception.UserInfoIncorrectException;
import com.example.viewWithSecurity.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    //as the return is responseEntity, it shows the exception on empty page(just body)
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ UserNotFoundException.class })
    public ModelAndView handleUserNotFoundException(
            Exception ex, WebRequest request) {

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/error");
        modelAndView.addObject("message",ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(UserInfoIncorrectException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ModelAndView handleUserIncorrect(Exception ex){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/error");
        modelAndView.addObject("message",ex.getMessage());
        return modelAndView;

    }

}
