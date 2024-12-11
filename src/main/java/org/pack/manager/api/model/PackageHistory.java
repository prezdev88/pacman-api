package org.pack.manager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PackageHistory extends UpgradePackage {

    private String action;
    private Datetime datetime;

    public PackageHistory(String name, String version, String newVersion, String action, OffsetDateTime dateTime) {
        super(name, version, newVersion);
        this.action = action;
        datetime = new Datetime(dateTime);
    }
}
