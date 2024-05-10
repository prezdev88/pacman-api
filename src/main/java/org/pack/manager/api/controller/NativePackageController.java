package org.pack.manager.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pack.manager.api.model.response.ExplicitInstalledPackagesResponse;
import org.pack.manager.api.model.response.LiteExplicitInstalledPackagesResponse;
import org.pack.manager.api.model.response.PackageByNameResponse;
import org.pack.manager.api.model.response.UpgradePackagesResponse;
import org.pack.manager.api.service.PackageService;
import org.pack.manager.api.util.TimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/native/packages")
@Tag(name = "Native packages", description = "Native packages API")
public class NativePackageController {

    private final PackageService nativePackageServiceImpl;

    @GetMapping("/installed/explicit")
    @Operation(summary = "Get explicit installed packages")
    public ResponseEntity<ExplicitInstalledPackagesResponse> getExplicitInstalledPackages() {
        TimeUtil.start("[native] get explicit installed packages");
        ExplicitInstalledPackagesResponse explicitInstalledPackagesResponse = new ExplicitInstalledPackagesResponse();
        explicitInstalledPackagesResponse.setPackages(nativePackageServiceImpl.getExplicitInstalledPackages());
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(explicitInstalledPackagesResponse);
    }

    @GetMapping("/installed/explicit/lite")
    @Operation(summary = "Get explicit installed packages (lite)")
    public ResponseEntity<LiteExplicitInstalledPackagesResponse> getLiteExplicitInstalledPackages() {
        TimeUtil.start("[native] get lite explicit installed packages");
        LiteExplicitInstalledPackagesResponse liteExplicitInstalledPackagesResponse = new LiteExplicitInstalledPackagesResponse();
        liteExplicitInstalledPackagesResponse.setPackages(nativePackageServiceImpl.getExplicitLiteInstalledPackages());
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(liteExplicitInstalledPackagesResponse);
    }

    @GetMapping("/upgrade")
    @Operation(summary = "Get packages to upgrade")
    public ResponseEntity<UpgradePackagesResponse> getUpgradePackages(@RequestParam("password") String password) {
        TimeUtil.start("[native] get upgrade packages");
        UpgradePackagesResponse upgradePackagesResponse = new UpgradePackagesResponse();
        upgradePackagesResponse.setPackages(nativePackageServiceImpl.getUpgradePackages(password));
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(upgradePackagesResponse);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get package by name")
    public ResponseEntity<PackageByNameResponse> getPackageByName(@PathVariable("name") String name) {
        TimeUtil.start("[native] get package by name \"" + name + "\"");
        PackageByNameResponse packageByNameResponse = new PackageByNameResponse();
        packageByNameResponse.setPack(nativePackageServiceImpl.getPackageBy(name));
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(packageByNameResponse);
    }

}
