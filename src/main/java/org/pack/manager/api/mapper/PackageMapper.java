package org.pack.manager.api.mapper;

import java.util.HashMap;
import java.util.List;

import org.pack.manager.api.model.Package;

public interface PackageMapper {
    List<Package> map(List<String> output);

    Package map(HashMap<Integer, String> packageHashMap);
}
