package org.pack.manager.api.controller;


import org.pack.manager.api.model.response.ExplicitInstalledPackagesResponse;
import org.pack.manager.api.model.response.LiteExplicitInstalledPackagesResponse;
import org.pack.manager.api.model.response.PackageByNameResponse;
import org.pack.manager.api.model.response.UpgradePackagesResponse;
import org.pack.manager.api.service.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/pacman/packages")
public class PacmanController {

    private final PackageService packageService;

    @GetMapping("/installed/explicit")
    public ResponseEntity<ExplicitInstalledPackagesResponse> getExplicitInstalledPackages() {
        ExplicitInstalledPackagesResponse explicitInstalledPackagesResponse = new ExplicitInstalledPackagesResponse();
        explicitInstalledPackagesResponse.setPackages(packageService.getExplicitInstalledPackages());
        return ResponseEntity.ok().body(explicitInstalledPackagesResponse);
    }

    @GetMapping("/installed/explicit/lite")
    public ResponseEntity<LiteExplicitInstalledPackagesResponse> getLiteExplicitInstalledPackages() {
        LiteExplicitInstalledPackagesResponse liteExplicitInstalledPackagesResponse = new LiteExplicitInstalledPackagesResponse();
        liteExplicitInstalledPackagesResponse.setPackages(packageService.getExplicitLiteInstalledPackages());
        return ResponseEntity.ok().body(liteExplicitInstalledPackagesResponse);
    }

    // TODO: throw exception when password is wrong
    // TODO: throw exception when no upgrade package
    @GetMapping("/upgrade")
    public ResponseEntity<UpgradePackagesResponse> getUpgradePackages(@RequestParam("password") String password) {
        UpgradePackagesResponse upgradePackagesResponse = new UpgradePackagesResponse();
        upgradePackagesResponse.setPackages(packageService.getUpgradePackages(password));
        return ResponseEntity.ok().body(upgradePackagesResponse);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PackageByNameResponse> getPackageByName(@PathVariable("name") String name) {
        PackageByNameResponse packageByNameResponse = new PackageByNameResponse();
        packageByNameResponse.setPack(packageService.getPackageBy(name));
        return ResponseEntity.ok().body(packageByNameResponse);
    }

}
