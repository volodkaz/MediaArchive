package org.vladimir.homeArchive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name){
        System.out.println();
        return "Привет " + name;
    }

}
