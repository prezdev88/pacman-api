package org.pack.manager.api.mapper.impl;

import lombok.Getter;
import lombok.Setter;
import org.pack.manager.api.mapper.SearchPackageMapper;
import org.pack.manager.api.model.SearchPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@Service
public class SearchPackageMapperImpl implements SearchPackageMapper {
    @Override
    public List<SearchPackage> map(List<String> output) {
        List<SearchPackage> searchPackages = new ArrayList<>();

        output.forEach(line -> {
            SearchPackage litePackage = this.map(line);
            searchPackages.add(litePackage);
        });

        searchPackages.sort(Comparator.comparing(SearchPackage::getName));

        return searchPackages;
    }

    @Override
    public SearchPackage map(String line) {
        String[] split = line.split(" ")[0].split("/");

        String source = split[0].trim();
        String name = split[1].trim();

        return new SearchPackage(name, source);
    }
}
