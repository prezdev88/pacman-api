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
        GroupPackage pack = new GroupPackage();

        pack.setRepository(groupPackageHashMap.get(0));
        pack.setName(groupPackageHashMap.get(1));
        pack.setVersion(groupPackageHashMap.get(2));
        pack.setDescription(groupPackageHashMap.get(3));
        pack.setArchitecture(groupPackageHashMap.get(4));
        pack.setUrl(groupPackageHashMap.get(5));
        pack.setGroups(groupPackageHashMap.get(7));
        pack.setProvides(groupPackageHashMap.get(8));
        pack.setDepends(groupPackageHashMap.get(9));
        pack.setOptionalDependencies(groupPackageHashMap.get(10));
        pack.setInConflictWith(groupPackageHashMap.get(11));
        pack.setReplaces(groupPackageHashMap.get(12));
        pack.setDownloadSize(sizeService.map(groupPackageHashMap, 13));
        pack.setInstallationSize(sizeService.map(groupPackageHashMap, 14));
        pack.setManager(groupPackageHashMap.get(15));
        pack.setCreationDate(groupPackageHashMap.get(16));
        pack.setValidatedBy(groupPackageHashMap.get(17));

        return pack;
    }
}
