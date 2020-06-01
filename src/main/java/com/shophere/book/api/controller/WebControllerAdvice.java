package com.shophere.book.api.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

// GET Method에서 Query Param 값을 Setter 말고 Field로 직접 Binding
@ControllerAdvice
public class WebControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}