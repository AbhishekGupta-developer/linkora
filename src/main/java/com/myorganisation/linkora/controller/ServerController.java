package com.myorganisation.linkora.controller;

import com.myorganisation.linkora.dto.response.ServerStatusResponseDto;
import com.myorganisation.linkora.service.ServerStatusService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServerController {

    private final ServerStatusService serverStatusService;

    public ServerController(ServerStatusService serverStatusService) {
        this.serverStatusService = serverStatusService;
    }

    @GetMapping
    public ResponseEntity<ServerStatusResponseDto> getServerStatus() {
        return new ResponseEntity<>(serverStatusService.getServerStatus(), HttpStatusCode.valueOf(200));
    }
}
