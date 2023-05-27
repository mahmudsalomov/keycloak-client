package uz.maniac4j.keycloakclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/anonymous")
    public HttpEntity<?> getAnonymous(){
        return ResponseEntity.ok("Hello Anonymous");
    }
    @GetMapping("/admin")
    public HttpEntity<?> getAdmin(){
        return ResponseEntity.ok("Hello Anonymous");
    }
    @GetMapping("/user")
    public HttpEntity<?> getUser(){
        return ResponseEntity.ok("Hello Anonymous");
    }
}
