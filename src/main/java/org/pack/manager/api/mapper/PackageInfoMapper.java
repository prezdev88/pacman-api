package org.pack.manager.api.mapper;

import org.pack.manager.api.model.PackageInfo;

import java.util.HashMap;
import java.util.List;

public interface PackageInfoMapper {
    PackageInfo mapToOne(List<String> output);

    List<PackageInfo> map(List<String> output);

    PackageInfo map(HashMap<Integer, String> packageHashMap);
}
