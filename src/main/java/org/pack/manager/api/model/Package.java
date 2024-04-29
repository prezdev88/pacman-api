package org.pack.manager.api.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Package {
    private String name;
    private String version;
    private String description;
    private String architecture;
    private String url;
    private String licences;
    private List<String> groups;
    private List<String> provides;
    private List<String> depends;
    private List<String> optionalDependencies;
    private List<String> requestedBy;
    private List<String> optionalFor;
    private List<String> inConflictWith;
    private List<String> replaces;
    private Size size;
    private String manager;
    private LocalDateTime creationDateTime;
    private LocalDateTime installDateTime;
    private String reasonInstallation;
    private boolean installationScript;
    private String validatedBy;
}
