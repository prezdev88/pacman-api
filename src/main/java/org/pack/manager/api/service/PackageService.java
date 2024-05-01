package org.pack.manager.api.service;

import java.util.List;

import org.pack.manager.api.model.LitePackage;
import org.pack.manager.api.model.Package;

import org.pack.manager.api.model.UpgradePackage;

public interface PackageService {
    List<Package> getExplicitInstalledPackages();

    List<LitePackage> getExplicitLiteInstalledPackages();

    List<UpgradePackage> getUpgradePackages(String rootPassword);

    Package getPackageBy(String name);
}
