package org.pack.manager.api.mapper.impl;

import org.pack.manager.api.mapper.InstalledPackageMapper;
import org.pack.manager.api.model.InstalledPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstalledPackageMapperImpl implements InstalledPackageMapper {
    @Override
    public List<InstalledPackage> map(List<String> output) {
        List<InstalledPackage> installedPackages = new ArrayList<>();

        output.forEach(line -> {
            InstalledPackage installedPackage = this.map(line);
            installedPackages.add(installedPackage);
        });

        return installedPackages;
    }

    @Override
    public InstalledPackage map(String line) {
        String name = line.split(" ")[0].trim();
        String version = line.split(" ")[1].trim();

        return new InstalledPackage(name, version);
    }
}
