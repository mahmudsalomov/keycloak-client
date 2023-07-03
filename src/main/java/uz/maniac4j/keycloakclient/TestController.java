package uz.maniac4j.keycloakclient;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.maniac4j.keycloakclient.model.UserData;
import uz.maniac4j.keycloakclient.security.CurrentUser;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/anonymous")
    public HttpEntity<?> getAnonymous(){
        return ResponseEntity.ok("Hello Anonymous");
    }
    @GetMapping("/admin")
    public HttpEntity<?> getAdmin(@CurrentUser User user){
        System.out.println(user);
        return ResponseEntity.ok("Hello Admin");
    }
    @GetMapping("/user")
    public HttpEntity<?> getUser(@CurrentUser User user){
        System.out.println(user);
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserData handleUserInfoRequest(Principal principal) {
        UserData user = new UserData();
        if (principal instanceof KeycloakPrincipal) {

            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            AccessToken token = kp.getKeycloakSecurityContext().getToken();
            user.setId(token.getId());
            user.setUserName(token.getName());
            Map<String, Object> otherClaims = token.getOtherClaims();
            user.setCustomAttributes(otherClaims);
        }
        return user;
    }
}
