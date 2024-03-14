package com.example.testSpring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Base64;

@Controller
@AllArgsConstructor
public class ClientController {
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    @GetMapping
    public String getCet(Model model, Principal principal){
        RestTemplate template = new RestTemplate();
        String accessToken = oAuth2AuthorizedClientService
                .loadAuthorizedClient("reg-client", principal.getName())
                .getAccessToken().getTokenValue();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<byte[]> response = template
                .exchange("http://localhost:8080/cat", HttpMethod.GET, entity, byte[].class);
        String base64Image = Base64.getEncoder().encodeToString(response.getBody());
        model.addAttribute("cat", base64Image);
        return "cat-page";
    }
}
