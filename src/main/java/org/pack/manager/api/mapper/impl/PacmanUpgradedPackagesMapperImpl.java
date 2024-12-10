package org.pack.manager.api.mapper.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.pack.manager.api.exception.NoUpgradedPackagesException;
import org.pack.manager.api.mapper.UpgradedPackagesMapper;
import org.pack.manager.api.model.UpgradedPackage;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class PacmanUpgradedPackagesMapperImpl implements UpgradedPackagesMapper {

    private DateTimeFormatter customFormatter;

    @PostConstruct
    public void init() {
        customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXX");
    }

    @Override
    public List<UpgradedPackage> map(List<String> output) {
        List<UpgradedPackage> upgradedPackages = output.stream()
                .map(this::map)
                .toList();

        if (upgradedPackages.isEmpty()) {
            throw new NoUpgradedPackagesException();
        }

        return upgradedPackages;
    }

    @Override
    public UpgradedPackage map(String line) {
        try {
            String[] parts = line.split(" ", 6);

            String dateTimeString = parts[0].replace("[", "").replace("]", "");
            OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeString, customFormatter);

            String name = parts[3];
            String version = parts[4].replace("(", "").replace(")", "");
            String newVersion = parts[5].replace("-> ", "").replace(")", "");

            return new UpgradedPackage(name, version, newVersion, dateTime);
        } catch (Exception e) {
            log.error("Error parsing line: {}", line, e);
            throw new IllegalArgumentException("Invalid log line format: " + line, e);
        }
    }
}
