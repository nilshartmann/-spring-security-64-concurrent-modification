package org.example.ccm;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RolesAllowed("USER")
    @GetMapping("/hello")
    String getHello() {
        return "hello";
    }

    @RolesAllowed("USER")
    @GetMapping("/goodbye")
    String getGoodbye() {
        return "goodbye";
    }

}
