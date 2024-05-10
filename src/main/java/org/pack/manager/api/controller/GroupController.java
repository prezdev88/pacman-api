package org.pack.manager.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.pack.manager.api.model.GroupPackage;
import org.pack.manager.api.model.response.*;
import org.pack.manager.api.service.GroupService;
import org.pack.manager.api.util.TimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/native/groups")
@Tag(name = "Group packages", description = "Group packages API")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    @Operation(summary = "Get groups")
    public ResponseEntity<GroupResponse> getGroups() {
        TimeUtil.start("[groups] get groups");
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setGroupNames(groupService.getGroupNames());
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(groupResponse);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get package names by group package name")
    public ResponseEntity<GroupPackageResponse> getPackageNamesBy(@PathVariable("name") String groupName) {
        TimeUtil.start("[groups] get package name by \"" + groupName + "\"");
        GroupPackageResponse groupPackageResponse = new GroupPackageResponse();
        groupPackageResponse.setPackages(groupService.getPackageNamesBy(groupName));
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(groupPackageResponse);
    }

    @GetMapping("/package/{name}")
    @Operation(summary = "Get packages by group package name")
    public ResponseEntity<GroupPackage> getPackageBy(@PathVariable("name") String packageName) {
        TimeUtil.start("[groups] get package by \"" + packageName + "\"");
        GroupPackage packageBy = groupService.getPackageBy(packageName);
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(packageBy);
    }

}
