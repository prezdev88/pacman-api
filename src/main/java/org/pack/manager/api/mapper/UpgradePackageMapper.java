package org.pack.manager.api.mapper;

import org.pack.manager.api.model.UpgradePackage;

import java.util.List;

public interface UpgradePackageMapper {
    List<UpgradePackage> map(List<String> output);

    UpgradePackage map(String line);
}
