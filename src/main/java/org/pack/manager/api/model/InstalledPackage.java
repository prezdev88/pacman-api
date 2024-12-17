package org.pack.manager.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InstalledPackage {
    private String name;
    private String version;
}
