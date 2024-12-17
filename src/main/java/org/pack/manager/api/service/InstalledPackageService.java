package org.pack.manager.api.service;

import org.pack.manager.api.model.InstalledPackage;

import java.util.List;

public interface InstalledPackageService {
    List<InstalledPackage> getInstalledPackages();
}
