package org.pack.manager.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpgradePackage {
    private String name;
    private String version;
    private String newVersion;
}
