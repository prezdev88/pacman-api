package org.pack.manager.api.service.impl;

import java.util.List;

import org.pack.manager.api.model.UpgradePackage;
import org.pack.manager.api.service.PackageService;
import org.springframework.stereotype.Service;

@Service
public class PacmanServiceImpl implements PackageService {

    @Override
    public List<Package> getInstalledPackages() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstalledPackages'");
    }

    @Override
    public List<UpgradePackage> getUpgradePackages() {
        throw new UnsupportedOperationException("Unimplemented method 'getUpgradePackages'");
    }

    @Override
    public Package getPackageBy(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getPackageBy'");
    }

}
