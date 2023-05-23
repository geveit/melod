package com.geveit.melod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeCcontroller {

    @GetMapping("/private")
    public  String privateRout(Principal principal) {
        return "Private";
    }

    @GetMapping("/public")
    public String publicRoute(Principal principal) {
        return "Public";
    }
}
