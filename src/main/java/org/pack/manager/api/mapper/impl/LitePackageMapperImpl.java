package org.pack.manager.api.mapper.impl;

import org.pack.manager.api.mapper.LitePackageMapper;
import org.pack.manager.api.model.LitePackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LitePackageMapperImpl implements LitePackageMapper {

    @Override
    public List<LitePackage> map(List<String> output) {
        List<LitePackage> litePackages = new ArrayList<>();

        output.forEach(line -> {
            LitePackage litePackage = this.map(line);
            litePackages.add(litePackage);
        });

        return litePackages;
    }

    @Override
    public LitePackage map(String line) {
        String name = line.split(" ")[0].trim();
        String version = line.split(" ")[1].trim();

        return new LitePackage(name, version);
    }
}
