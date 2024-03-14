package com.example.testOAuth2Res.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController()
public class ResourceController {
    @GetMapping("/cat")
    public ResponseEntity<byte[]>getImageCat() throws IOException {
        InputStream in = new FileInputStream("cat.jpg");
        byte[] image = in.readAllBytes();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, httpHeaders, HttpStatus.OK);
    }
    @GetMapping("/error")
    public ResponseEntity<String> errors(){

        return new ResponseEntity<>("error", HttpStatus.OK);
    }
}
