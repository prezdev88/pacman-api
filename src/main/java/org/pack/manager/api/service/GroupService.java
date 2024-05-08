package org.pack.manager.api.service;

import org.pack.manager.api.model.GroupPackage;

import java.util.List;

public interface GroupService {
    List<String> getGroupNames();

    List<String> getPackageNamesBy(String groupName);

    GroupPackage getPackageBy(String packageName);
}
