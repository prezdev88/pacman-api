package org.pack.manager.api.controller;

import lombok.AllArgsConstructor;
import org.pack.manager.api.model.response.ExplicitInstalledPackagesResponse;
import org.pack.manager.api.model.response.LiteExplicitInstalledPackagesResponse;
import org.pack.manager.api.model.response.PackageByNameResponse;
import org.pack.manager.api.model.response.UpgradePackagesResponse;
import org.pack.manager.api.service.PackageService;
import org.pack.manager.api.util.TimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/foreign/packages")
public class ForeignPackageController {

    private final PackageService foreignPackageServiceImpl;

    @GetMapping("/installed/explicit")
    public ResponseEntity<ExplicitInstalledPackagesResponse> getExplicitInstalledPackages() {
        TimeUtil.start("[foreign] get explicit installed packages");
        ExplicitInstalledPackagesResponse explicitInstalledPackagesResponse = new ExplicitInstalledPackagesResponse();
        explicitInstalledPackagesResponse.setPackages(foreignPackageServiceImpl.getExplicitInstalledPackages());
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(explicitInstalledPackagesResponse);
    }

    @GetMapping("/installed/explicit/lite")
    public ResponseEntity<LiteExplicitInstalledPackagesResponse> getLiteExplicitInstalledPackages() {
        TimeUtil.start("[foreign] get lite explicit installed packages");
        LiteExplicitInstalledPackagesResponse liteExplicitInstalledPackagesResponse = new LiteExplicitInstalledPackagesResponse();
        liteExplicitInstalledPackagesResponse.setPackages(foreignPackageServiceImpl.getExplicitLiteInstalledPackages());
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(liteExplicitInstalledPackagesResponse);
    }

    @GetMapping("/upgrade")
    public ResponseEntity<UpgradePackagesResponse> getUpgradePackages(@RequestParam("password") String password) {
        TimeUtil.start("[foreign] get upgrade packages");
        UpgradePackagesResponse upgradePackagesResponse = new UpgradePackagesResponse();
        upgradePackagesResponse.setPackages(foreignPackageServiceImpl.getUpgradePackages(password));
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(upgradePackagesResponse);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PackageByNameResponse> getPackageByName(@PathVariable("name") String name) {
        TimeUtil.start("[foreign] get package by name \"" + name + "\"");
        PackageByNameResponse packageByNameResponse = new PackageByNameResponse();
        packageByNameResponse.setPack(foreignPackageServiceImpl.getPackageBy(name));
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(packageByNameResponse);
    }

}
