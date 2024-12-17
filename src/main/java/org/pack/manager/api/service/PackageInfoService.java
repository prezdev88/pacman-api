package org.pack.manager.api.service;

import org.pack.manager.api.model.PackageInfo;

public interface PackageInfoService {
    PackageInfo get(String name);
}
