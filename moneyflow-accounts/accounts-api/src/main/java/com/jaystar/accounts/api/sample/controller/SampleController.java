package com.jaystar.accounts.api.sample.controller;

import com.jaystar.accounts.core.sample.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sample")
@RestController
public class SampleController {

    private final MyService myService;

    public SampleController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping
    public String sample() {
        return myService.message();
    }
}
