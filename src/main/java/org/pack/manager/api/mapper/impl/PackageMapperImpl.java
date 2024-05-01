package org.pack.manager.api.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import org.pack.manager.api.mapper.PackageMapper;
import org.pack.manager.api.model.Line;
import org.pack.manager.api.model.Package;
import org.pack.manager.api.service.SizeService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageMapperImpl implements PackageMapper {

    private final SizeService sizeService;

    @Override
    public List<Package> map(List<String> output) {
        int lineIndex = 0;
        List<Package> packages = new ArrayList<>();
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

    @Override
    public Package map(HashMap<Integer, String> packageHashMap) {
        Package pack = new Package();

        pack.setName(packageHashMap.get(0));
        pack.setVersion(packageHashMap.get(1));
        pack.setDescription(packageHashMap.get(2));
        pack.setArchitecture(packageHashMap.get(3));
        pack.setUrl(packageHashMap.get(4));
        pack.setLicences(packageHashMap.get(5));
        pack.setGroups(packageHashMap.get(6));
        pack.setProvides(packageHashMap.get(7));
        pack.setDepends(packageHashMap.get(8));
        pack.setOptionalDependencies(packageHashMap.get(9));
        pack.setRequestedBy(packageHashMap.get(10));
        pack.setOptionalFor(packageHashMap.get(11));
        pack.setInConflictWith(packageHashMap.get(12));
        pack.setReplaces(packageHashMap.get(13));
        pack.setSize(sizeService.map(packageHashMap));
        pack.setManager(packageHashMap.get(15));
        pack.setCreationDateTime(packageHashMap.get(16));
        pack.setInstallDateTime(packageHashMap.get(17));
        pack.setReasonInstallation(packageHashMap.get(18));
        pack.setInstallationScript(packageHashMap.get(19));
        pack.setValidatedBy(packageHashMap.get(20));

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

    private void addPackageToList(HashMap<Integer, String> packageHashMap, List<Package> packages) {
        Package pack = this.map(packageHashMap);
        packages.add(pack);
    }
}
