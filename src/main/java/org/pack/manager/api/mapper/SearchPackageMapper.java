package org.pack.manager.api.mapper;

import org.pack.manager.api.model.SearchPackage;

import java.util.List;

public interface SearchPackageMapper {
    List<SearchPackage> map(List<String> output);

    SearchPackage map(String line);
}
