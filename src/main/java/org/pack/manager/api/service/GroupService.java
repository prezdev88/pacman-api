package org.pack.manager.api.service;

import java.util.List;

public interface GroupService {
    List<String> getGroupNames();

    List<String> getPackageNamesBy(String groupName);
}
