package org.pack.manager.api.mapper;

import org.pack.manager.api.model.GroupPackage;

import java.util.List;

public interface GroupMapper {
    List<GroupPackage> map(List<String> output);

    List<String> mapToPackageNames(List<String> output);
}
