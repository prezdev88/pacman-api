package org.pack.manager.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.pack.manager.api.model.GetUpgradedPackagesRequest;
import org.pack.manager.api.model.UpgradedPackage;
import org.pack.manager.api.model.response.GeNativeUpgradedPackagesResponse;
import org.pack.manager.api.service.UpgradedPackagesService;
import org.pack.manager.api.util.TimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/native/packages/upgraded")
@Tag(name = "Upgraded native packages", description = "Upgraded native packages API")
public class NativeUpgradedPackageLogController {

    private final UpgradedPackagesService pacmanUpgradedPackagesServiceImpl;

    @GetMapping
    @Operation(summary = "Get the upgraded packages by month and year")
    public ResponseEntity<GeNativeUpgradedPackagesResponse> getUpgradedPackages(@ModelAttribute GetUpgradedPackagesRequest getUpgradedPackagesRequest) {
        TimeUtil.start("[native] get upgraded packages by month and year");
        List<UpgradedPackage> upgradedPackages = pacmanUpgradedPackagesServiceImpl.getUpgradedPackages(getUpgradedPackagesRequest);
        TimeUtil.stopAndPrintElapsedTime("[native] get upgraded packages by month and year");

        return ResponseEntity.ok().body(new GeNativeUpgradedPackagesResponse(upgradedPackages));
    }


}
