package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("test")
@RequestMapping("/getPort")
public class InfoControllerTest {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping()
    public ResponseEntity<Integer> getServerPort() {
        return ResponseEntity.ok(serverPort);
    }
}
