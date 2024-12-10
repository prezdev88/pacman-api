package org.pack.manager.api.service;

import org.pack.manager.api.model.GetUpgradedPackagesRequest;
import org.pack.manager.api.model.UpgradedPackage;

import java.util.List;

public interface UpgradedPackagesService {
    List<UpgradedPackage> getUpgradedPackages(GetUpgradedPackagesRequest getUpgradedPackagesRequest);
}
