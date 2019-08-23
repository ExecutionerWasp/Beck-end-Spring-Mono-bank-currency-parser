package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin
 **/
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/shutdown")
    public void main() {
        System.exit(0);
    }
}
