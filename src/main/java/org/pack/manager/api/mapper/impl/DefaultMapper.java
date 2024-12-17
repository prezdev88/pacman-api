package org.pack.manager.api.mapper.impl;

import lombok.AllArgsConstructor;
import org.pack.manager.api.mapper.PackageSourceMapper;
import org.pack.manager.api.model.PackageInfo;
import org.pack.manager.api.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class DefaultMapper implements PackageSourceMapper {

    private final SizeService sizeService;

    @Override
    public PackageInfo map(HashMap<Integer, String> packageHashMap) {
        PackageInfo packageInfo = new PackageInfo();

        packageInfo.setRepository(packageHashMap.get(0));
        packageInfo.setName(packageHashMap.get(1));
        packageInfo.setVersion(packageHashMap.get(2));
        packageInfo.setDescription(packageHashMap.get(3));
        packageInfo.setArchitecture(packageHashMap.get(4));
        packageInfo.setUrl(packageHashMap.get(5));
        packageInfo.setLicences(packageHashMap.get(6));
        packageInfo.setGroups(packageHashMap.get(7));
        packageInfo.setProvides(packageHashMap.get(8));
        packageInfo.setDepends(packageHashMap.get(9));
        packageInfo.setOptionalDependencies(packageHashMap.get(10));
        packageInfo.setInConflictWith(packageHashMap.get(11));
        packageInfo.setReplaces(packageHashMap.get(12));
        packageInfo.setDownloadSize(sizeService.map(packageHashMap, 13));
        packageInfo.setInstallSize(sizeService.map(packageHashMap, 14));
        packageInfo.setMaintainer(packageHashMap.get(15));
        packageInfo.setCreationDateTime(packageHashMap.get(16));
        packageInfo.setValidatedBy(packageHashMap.get(17));

        return packageInfo;
    }
}
