package org.clipper.accessdb;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
public class AuthController {
    @PostMapping("/auth/login")
    public String login() {
        return "OK";
    }
}
