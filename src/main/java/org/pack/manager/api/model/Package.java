package org.pack.manager.api.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private String creationDateTime;
    private String installDateTime;
    private String reasonInstallation;
    private boolean installationScript;
    private String validatedBy;

    public void setGroups(String groups) {
        this.groups = Arrays.stream(groups.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setProvides(String provides) {
        this.provides = Arrays.stream(provides.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setDepends(String depends) {
        this.depends = Arrays.stream(depends.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setOptionalDependencies(String optionalDependencies) {
        this.optionalDependencies = Arrays.asList(optionalDependencies.split("\\n"));
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = Arrays.stream(requestedBy.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setOptionalFor(String optionalFor) {
        this.optionalFor = Arrays.stream(optionalFor.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setInConflictWith(String inConflictWith) {
        this.inConflictWith = Arrays.stream(inConflictWith.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setReplaces(String replaces) {
        this.replaces = Arrays.stream(replaces.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setInstallationScript(String installationScript) {
        this.installationScript = !installationScript.equalsIgnoreCase("NO");
    }
}
