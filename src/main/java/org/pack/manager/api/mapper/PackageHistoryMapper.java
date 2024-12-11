package org.pack.manager.api.mapper;

import org.pack.manager.api.model.PackageHistory;

import java.util.List;

public interface PackageHistoryMapper {
    List<PackageHistory> map(List<String> output);

    PackageHistory map(String line);
}
