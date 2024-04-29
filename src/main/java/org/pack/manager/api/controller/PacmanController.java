package org.pack.manager.api.controller;

import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.service.CommandRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PacmanController {

    private final CommandRunner commandRunner;

    @GetMapping("/test")
    public ResponseEntity test() {
        CommandRequest commandRequest = new CommandRequest();

        commandRequest.setCommand("pacman -Sy");
        commandRequest.setRootPassword("123123");

        CommandResult commandResult = commandRunner.exec(commandRequest);
        return ResponseEntity.ok().body(commandResult);
    }

}
