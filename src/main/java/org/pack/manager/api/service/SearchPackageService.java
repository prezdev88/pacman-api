package org.pack.manager.api.service;

import org.pack.manager.api.model.SearchPackage;

import java.util.List;

public interface SearchPackageService {
    List<SearchPackage> search(String name);
}
