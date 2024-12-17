package org.pack.manager.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.pack.manager.api.model.SearchPackage;
import org.pack.manager.api.model.response.SearchPackageResponse;
import org.pack.manager.api.service.SearchPackageService;
import org.pack.manager.api.util.TimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/packages")
@Tag(name = "Search Packages", description = "Search Packages API")
public class SearchPackageController {

    private final SearchPackageService searchPackageService;

    @GetMapping("/{name}")
    @Operation(summary = "Search package by name")
    public ResponseEntity<SearchPackageResponse> searchPackage(@PathVariable("name") String name) {
        String title = "[packages] search package by name";

        TimeUtil.start(title);
        List<SearchPackage> search = searchPackageService.search(name);
        TimeUtil.stopAndPrintElapsedTime(title);

        return ResponseEntity.ok().body(new SearchPackageResponse(search));
    }
}
