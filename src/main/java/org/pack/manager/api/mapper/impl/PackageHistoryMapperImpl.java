package org.pack.manager.api.mapper.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.pack.manager.api.exception.NoPackageHistoryException;
import org.pack.manager.api.mapper.PackageHistoryMapper;
import org.pack.manager.api.model.PackageHistory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class PackageHistoryMapperImpl implements PackageHistoryMapper {

    private DateTimeFormatter customFormatter;

    @PostConstruct
    public void init() {
        customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXX");
    }

    @Override
    public List<PackageHistory> map(List<String> output) {
        List<PackageHistory> packageHistoryList = output.stream()
                .map(this::map)
                .toList();

        if (packageHistoryList.isEmpty()) {
            throw new NoPackageHistoryException();
        }

        return packageHistoryList;
    }

    @Override
    public PackageHistory map(String line) {
        try {
            String[] parts = line.split(" ", 6);

            String dateTimeString = parts[0].replace("[", "").replace("]", "");
            OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeString, customFormatter);

            String name = parts[3];
            String version = parts[4].replace("(", "").replace(")", "");
            String newVersion = loadNewVersion(parts);
            String action = parts[2];

            return new PackageHistory(name, version, newVersion, action, dateTime);
        } catch (Exception e) {
            log.error("Error parsing line: {}", line, e);
            throw new IllegalArgumentException("Invalid log line format: " + line, e);
        }
    }

    private String loadNewVersion(String[] parts) {
        try {
            return parts[5].replace("-> ", "").replace(")", "");
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
}
