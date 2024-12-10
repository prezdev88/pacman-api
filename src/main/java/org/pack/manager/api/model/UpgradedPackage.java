package org.pack.manager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UpgradedPackage extends UpgradePackage {

    private OffsetDateTime dateTime;

    public UpgradedPackage(String name, String version, String newVersion, OffsetDateTime dateTime) {
        super(name, version, newVersion);
        this.dateTime = dateTime;
    }
}
