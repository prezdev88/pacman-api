package org.pack.manager.api.mapper;

import org.pack.manager.api.model.PackageInfo;

import java.util.HashMap;

public interface PackageSourceMapper {
    PackageInfo map(HashMap<Integer, String> packageHashMap);
}
