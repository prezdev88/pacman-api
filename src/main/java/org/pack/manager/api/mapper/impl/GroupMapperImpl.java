package org.pack.manager.api.mapper.impl;

import org.pack.manager.api.mapper.GroupMapper;
import org.pack.manager.api.model.GroupPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupMapperImpl implements GroupMapper {

    @Override
    public List<GroupPackage> map(List<String> output) {
        List<GroupPackage> groupPackages = new ArrayList<>();

        output.forEach(line -> groupPackages.add(new GroupPackage(line)));

        return groupPackages;
    }

    @Override
    public List<String> mapToPackageNames(List<String> output) {
        List<String> packageNames = new ArrayList<>();

        output.forEach(line -> packageNames.add(line.split(" ")[1]));

        Collections.sort(packageNames);

        return packageNames;
    }
}
