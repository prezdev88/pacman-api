package org.pack.manager.api.mapper.impl;

import org.pack.manager.api.mapper.UpgradePackageMapper;
import org.pack.manager.api.model.UpgradePackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpgradePackageMapperImpl implements UpgradePackageMapper {

    @Override
    public List<UpgradePackage> map(List<String> output) {
        List<UpgradePackage> upgradePackages = new ArrayList<>();

        output.forEach(line -> {
            UpgradePackage upgradePackage = this.map(line);
            upgradePackages.add(upgradePackage);
        });

        return upgradePackages;
    }

    @Override
    public UpgradePackage map(String upgradePackageLine) {
        String name = upgradePackageLine.split(" ")[0].trim();
        String currentVersion = upgradePackageLine.split(" ")[1].trim();
        String newVersion = upgradePackageLine.split(" ")[3].trim();

        return new UpgradePackage(name, currentVersion, newVersion);
    }
}
