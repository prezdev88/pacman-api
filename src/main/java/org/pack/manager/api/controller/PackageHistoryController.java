package org.pack.manager.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.pack.manager.api.model.PackageHistory;
import org.pack.manager.api.model.response.GePackageHistoryResponse;
import org.pack.manager.api.service.PackageHistoryService;
import org.pack.manager.api.util.TimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/packages")
@Tag(name = "Package History", description = "Package History API")
public class PackageHistoryController {

    private final PackageHistoryService packageHistoryService;

    @GetMapping("/{name}/history")
    @Operation(summary = "Get package history by package name")
    public ResponseEntity<GePackageHistoryResponse> getHistoryBy(@PathVariable("name") String name) {
        TimeUtil.start("[packages] get package history by package name");
        List<PackageHistory> packageHistory = packageHistoryService.getPackageHistory(name);
        TimeUtil.stopAndPrintElapsedTime("[packages] get package history by package name");

        return ResponseEntity.ok().body(new GePackageHistoryResponse(packageHistory));
    }
}
