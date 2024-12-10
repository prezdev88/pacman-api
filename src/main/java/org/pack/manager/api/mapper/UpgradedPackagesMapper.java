package org.pack.manager.api.mapper;

import org.pack.manager.api.model.UpgradedPackage;

import java.util.List;

public interface UpgradedPackagesMapper {
    List<UpgradedPackage> map(List<String> output);

    UpgradedPackage map(String line);
}
