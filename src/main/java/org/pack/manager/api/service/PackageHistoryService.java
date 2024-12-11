package org.pack.manager.api.service;

import org.pack.manager.api.model.PackageHistory;

import java.util.List;

public interface PackageHistoryService {
    List<PackageHistory> getPackageHistory(String packageName);
}
