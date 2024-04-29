package org.pack.manager.api.service;

import java.util.List;

import org.pack.manager.api.model.UpgradePackage;

public interface PackageService {
    List<Package> getInstalledPackages();

    List<UpgradePackage> getUpgradePackages();

    Package getPackageBy(String name);
}
