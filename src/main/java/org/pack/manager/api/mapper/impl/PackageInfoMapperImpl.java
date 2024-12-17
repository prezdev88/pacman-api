package org.pack.manager.api.mapper.impl;

import lombok.AllArgsConstructor;
import org.pack.manager.api.exception.PackageNotFoundException;
import org.pack.manager.api.mapper.PackageInfoMapper;
import org.pack.manager.api.model.Line;
import org.pack.manager.api.model.PackageInfo;
import org.pack.manager.api.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class PackageInfoMapperImpl implements PackageInfoMapper {

    private final SizeService sizeService;

    @Override
    public PackageInfo mapToOne(List<String> output) {
        List<PackageInfo> packages = map(output);

        if (packages.isEmpty()) {
            throw new PackageNotFoundException();
        }

        return packages.getFirst();
    }

    @Override
    public List<PackageInfo> map(List<String> output) {
        int lineIndex = 0;
        List<PackageInfo> packages = new ArrayList<>();
        HashMap<Integer, String> packageHashMap = new HashMap<>();

        for (String stringLine : output) {
            if (stringLine.isEmpty()) {
                addPackageToList(packageHashMap, packages);

                lineIndex = 0;
                packageHashMap = new HashMap<>();

                continue;
            }

            Line line = processLine(stringLine, packageHashMap, lineIndex);
            packageHashMap.put(line.getIndex(), line.getValue());

            if (line.isIncreaseIndex()) {
                lineIndex++;
            }
        }

        return packages;
    }

    private void addPackageToList(HashMap<Integer, String> packageHashMap, List<PackageInfo> packages) {
        PackageInfo pack = this.map(packageHashMap);
        packages.add(pack);
    }

    @Override
    public PackageInfo map(HashMap<Integer, String> packageHashMap) {
        PackageInfo pack = new PackageInfo();

        pack.setRepository(packageHashMap.get(0));
        pack.setName(packageHashMap.get(1));
        pack.setVersion(packageHashMap.get(2));
        pack.setDescription(packageHashMap.get(3));
        pack.setLicences(packageHashMap.get(6));
        pack.setGroups(packageHashMap.get(7));
        pack.setProvides(packageHashMap.get(8));
        pack.setDepends(packageHashMap.get(9));
        pack.setOptionalDependencies(packageHashMap.get(10));

        if ("aur".equalsIgnoreCase(pack.getRepository())) {
            pack.setUrl(packageHashMap.get(5));
            pack.setBuildDependencies(packageHashMap.get(10));
            pack.setCheckDependencies(packageHashMap.get(11));
            pack.setInConflictWith(packageHashMap.get(12));
            pack.setReplaces(packageHashMap.get(13));
            pack.setAurUrl(packageHashMap.get(14));
            pack.setFirstSubmitted(packageHashMap.get(15));
            pack.setKeywords(packageHashMap.get(16));
            pack.setLastModified(packageHashMap.get(17));
            pack.setMaintainer(packageHashMap.get(18));
            pack.setMaintainerPopularity(Double.parseDouble(packageHashMap.get(19)));
            pack.setVotes(Integer.parseInt(packageHashMap.get(20)));
            pack.setOutOfDate(!"No".equalsIgnoreCase(packageHashMap.get(21)));
        } else {
            pack.setArchitecture(packageHashMap.get(4));
            pack.setUrl(packageHashMap.get(5));
            pack.setInConflictWith(packageHashMap.get(11));
            pack.setReplaces(packageHashMap.get(12));
            pack.setDownloadSize(sizeService.map(packageHashMap, 13));
            pack.setInstallSize(sizeService.map(packageHashMap, 14));
            pack.setMaintainer(packageHashMap.get(15));
            pack.setCreationDateTime(packageHashMap.get(16));
            pack.setValidatedBy(packageHashMap.get(17));
        }

        return pack;
    }

    private Line processLine(String line, HashMap<Integer, String> packageHashMap, int index) {
        try {
            String value = line.split(" : ")[1].trim();
            return new Line(index, value, true);
        } catch (ArrayIndexOutOfBoundsException e) {
            String previousValue = packageHashMap.get(--index);
            String value = previousValue + "\n" + line.trim();
            return new Line(index, value, false);
        }
    }
}
