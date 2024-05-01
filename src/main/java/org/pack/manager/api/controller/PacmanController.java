package org.pack.manager.api.controller;

import java.util.List;

import org.pack.manager.api.model.UpgradePackage;
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
        List<UpgradePackage> upgradePackages = packageService.getUpgradePackages();
        return ResponseEntity.ok().body(upgradePackages);
    }

}
