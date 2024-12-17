package org.pack.manager.api.mapper.impl;

import lombok.AllArgsConstructor;
import org.pack.manager.api.exception.PackageNotFoundException;
import org.pack.manager.api.mapper.PackageSourceMapper;
import org.pack.manager.api.mapper.PackageInfoMapper;
import org.pack.manager.api.model.Line;
import org.pack.manager.api.model.PackageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class PackageInfoMapperImpl implements PackageInfoMapper {

    private final PackageSourceMapper aurMapper;
    private final PackageSourceMapper defaultMapper;

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
        String repository = packageHashMap.get(0);

        if ("aur".equalsIgnoreCase(repository)) {
            return aurMapper.map(packageHashMap);
        }

        return defaultMapper.map(packageHashMap);
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
