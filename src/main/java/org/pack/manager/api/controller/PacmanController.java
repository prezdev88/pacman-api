package org.pack.manager.api.controller;


import org.pack.manager.api.service.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PacmanController {

    private final PackageService packageService;

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok().body(packageService.getUpgradePackages("123123"));
    }

}
