package org.pack.manager.api.mapper.impl;

import lombok.AllArgsConstructor;
import org.pack.manager.api.mapper.GroupMapper;
import org.pack.manager.api.model.GroupPackage;
import org.pack.manager.api.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupMapperImpl implements GroupMapper {

    private final SizeService sizeService;

    @Override
    public GroupPackage map(List<String> output) {
        HashMap<Integer, String> groupPackageHashMap = new HashMap<>();

        for (int i = 0; i < output.size(); i++) {
            String line = output.get(i);

            if (line.isEmpty()) {
                break;
            }

            groupPackageHashMap.put(i, output.get(i).split(" : ")[1].trim());
        }

        return this.map(groupPackageHashMap);
    }

    @Override
    public List<String> mapToPackageNames(List<String> output) {
        List<String> packageNames = new ArrayList<>();

        output.forEach(line -> packageNames.add(line.split(" ")[1]));

        Collections.sort(packageNames);

        return packageNames;
    }

    @Override
    public GroupPackage map(HashMap<Integer, String> groupPackageHashMap) {
        GroupPackage groupPackage = new GroupPackage();

        groupPackage.setRepository(groupPackageHashMap.get(0));
        groupPackage.setName(groupPackageHashMap.get(1));
        groupPackage.setVersion(groupPackageHashMap.get(2));
        groupPackage.setDescription(groupPackageHashMap.get(3));
        groupPackage.setArchitecture(groupPackageHashMap.get(4));
        groupPackage.setUrl(groupPackageHashMap.get(5));
        groupPackage.setGroups(groupPackageHashMap.get(7));
        groupPackage.setProvides(groupPackageHashMap.get(8));
        groupPackage.setDepends(groupPackageHashMap.get(9));
        groupPackage.setOptionalDependencies(groupPackageHashMap.get(10));
        groupPackage.setInConflictWith(groupPackageHashMap.get(11));
        groupPackage.setReplaces(groupPackageHashMap.get(12));
        groupPackage.setDownloadSize(sizeService.map(groupPackageHashMap, 13));
        groupPackage.setInstallationSize(sizeService.map(groupPackageHashMap, 14));
        groupPackage.setManager(groupPackageHashMap.get(15));
        groupPackage.setCreationDate(groupPackageHashMap.get(16));
        groupPackage.setValidatedBy(groupPackageHashMap.get(17));

        return groupPackage;
    }
}
