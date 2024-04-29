package org.pack.manager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpgradePackage extends Package {

    private String newVersion;

    public UpgradePackage(String name, String version, String newVersion) {
        super();

        super.setName(name);
        super.setVersion(version);
        this.newVersion = newVersion;
    }
}
