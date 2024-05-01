package org.pack.manager.api.mapper;

import org.pack.manager.api.model.LitePackage;

import java.util.List;

public interface LitePackageMapper {
    List<LitePackage> map(List<String> output);

    LitePackage map(String line);
}
