package org.pack.manager.api.mapper.impl;

import org.pack.manager.api.mapper.PackageSourceMapper;
import org.pack.manager.api.model.PackageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AurMapper implements PackageSourceMapper {
    @Override
    public PackageInfo map(HashMap<Integer, String> packageHashMap) {
        PackageInfo packageInfo = new PackageInfo();

        packageInfo.setRepository(packageHashMap.get(0));
        packageInfo.setName(packageHashMap.get(1));
        packageInfo.setVersion(packageHashMap.get(2));
        packageInfo.setDescription(packageHashMap.get(3));
        packageInfo.setUrl(packageHashMap.get(4));
        packageInfo.setLicences(packageHashMap.get(5));
        packageInfo.setGroups(packageHashMap.get(6));
        packageInfo.setProvides(packageHashMap.get(7));
        packageInfo.setDepends(packageHashMap.get(8));
        packageInfo.setOptionalDependencies(packageHashMap.get(9));
        packageInfo.setBuildDependencies(packageHashMap.get(10));
        packageInfo.setCheckDependencies(packageHashMap.get(11));
        packageInfo.setInConflictWith(packageHashMap.get(12));
        packageInfo.setReplaces(packageHashMap.get(13));
        packageInfo.setAurUrl(packageHashMap.get(14));
        packageInfo.setFirstSubmitted(packageHashMap.get(15));
        packageInfo.setKeywords(packageHashMap.get(16));
        packageInfo.setLastModified(packageHashMap.get(17));
        packageInfo.setMaintainer(packageHashMap.get(18));
        packageInfo.setMaintainerPopularity(Double.parseDouble(packageHashMap.get(19)));
        packageInfo.setVotes(Integer.parseInt(packageHashMap.get(20)));
        packageInfo.setOutOfDate(!"No".equalsIgnoreCase(packageHashMap.get(21)));

        return packageInfo;
    }
}
