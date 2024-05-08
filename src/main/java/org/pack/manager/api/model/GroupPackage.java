package org.pack.manager.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupPackage {

    private String repository;
    private String name;
    private String version;
    private String description;
    private String architecture;
    private String url;
    private List<String> licenses;
    private List<String> groups;
    private List<String> provides;
    private List<String> depends;
    private List<String> optionalDependencies;
    private List<String> inConflictWith;
    private String replaces;
    private Size downloadSize;
    private Size installationSize;
    private String manager;
    private String creationDate;
    private String validatedBy;

    public GroupPackage(String name) {
        this.name = name;
    }

    public void setLicenses(String licenses) {
        this.licenses = Arrays.stream(licenses.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

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

    public void setOptionalDependencies(String optionalFor) {
        this.optionalDependencies = Arrays.stream(optionalFor.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setInConflictWith(String inConflictWith) {
        this.inConflictWith = Arrays.stream(inConflictWith.split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
