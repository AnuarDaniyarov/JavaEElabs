package com.example.labSpring.Controller;

import com.example.labSpring.Services.LabSpringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Controller
public class LabSpringController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }
}
