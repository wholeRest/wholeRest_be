package org.example.rest_back.testController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping(path = "/test")
    public String test(){
        return "welcome!";
    }
}
