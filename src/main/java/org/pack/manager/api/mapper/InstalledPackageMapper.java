package org.pack.manager.api.mapper;

import org.pack.manager.api.model.InstalledPackage;

import java.util.List;

public interface InstalledPackageMapper {
    List<InstalledPackage> map(List<String> out);

    InstalledPackage map(String line);
}
