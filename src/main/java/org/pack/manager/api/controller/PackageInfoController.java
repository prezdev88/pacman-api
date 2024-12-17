package org.pack.manager.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.pack.manager.api.model.PackageInfo;
import org.pack.manager.api.model.response.PackageInfoResponse;
import org.pack.manager.api.service.PackageInfoService;
import org.pack.manager.api.util.TimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/packages/info")
@Tag(name = "Get Package info (All sources)", description = "Get Package info API")
public class PackageInfoController {

    private final PackageInfoService packageInfoService;

    @GetMapping("/{name}")
    @Operation(summary = "Get Package info by name")
    public ResponseEntity<PackageInfoResponse> getPackageInfo(@PathVariable("name") String name) {
        String title = "[packages] get Package info by name";

        TimeUtil.start(title);
        PackageInfo packageInfo = packageInfoService.get(name);
        TimeUtil.stopAndPrintElapsedTime(title);

        return ResponseEntity.ok().body(new PackageInfoResponse(packageInfo));
    }
}
