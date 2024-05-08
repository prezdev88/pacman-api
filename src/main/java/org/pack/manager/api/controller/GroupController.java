package org.pack.manager.api.controller;

import lombok.AllArgsConstructor;
import org.pack.manager.api.model.GroupPackage;
import org.pack.manager.api.model.response.*;
import org.pack.manager.api.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/native/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<GroupResponse> getGroups() {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setGroupNames(groupService.getGroupNames());
        return ResponseEntity.ok().body(groupResponse);
    }

    @GetMapping("/{name}")
    public ResponseEntity<GroupPackageResponse> getPackageNamesBy(@PathVariable("name") String groupName) {
        GroupPackageResponse groupPackageResponse = new GroupPackageResponse();
        groupPackageResponse.setPackages(groupService.getPackageNamesBy(groupName));
        return ResponseEntity.ok().body(groupPackageResponse);
    }

    @GetMapping("/package/{name}")
    public ResponseEntity<GroupPackage> getPackageBy(@PathVariable("name") String packageName) {
        return ResponseEntity.ok().body(groupService.getPackageBy(packageName));
    }

}
