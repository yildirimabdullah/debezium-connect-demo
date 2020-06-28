package com.debezium.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public RedirectView redirectToSwaggerUI() {
        return new RedirectView("/swagger-ui.html");
    }

}
