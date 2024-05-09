package org.pack.manager.api.controller;

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
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<GroupResponse> getGroups() {
        TimeUtil.start("[groups] get groups");
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setGroupNames(groupService.getGroupNames());
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(groupResponse);
    }

    @GetMapping("/{name}")
    public ResponseEntity<GroupPackageResponse> getPackageNamesBy(@PathVariable("name") String groupName) {
        TimeUtil.start("[groups] get package name by \"" + groupName + "\"");
        GroupPackageResponse groupPackageResponse = new GroupPackageResponse();
        groupPackageResponse.setPackages(groupService.getPackageNamesBy(groupName));
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(groupPackageResponse);
    }

    @GetMapping("/package/{name}")
    public ResponseEntity<GroupPackage> getPackageBy(@PathVariable("name") String packageName) {
        TimeUtil.start("[groups] get package by \"" + packageName + "\"");
        GroupPackage packageBy = groupService.getPackageBy(packageName);
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.ok().body(packageBy);
    }

}
