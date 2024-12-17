package org.pack.manager.api.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageInfo {

    private String repository;
    private String name;
    private String version;
    private String description;
    private String architecture;
    private String url;
    private List<String> licences;
    private List<String> groups;
    private List<String> provides;
    private List<String> depends;
    private List<String> optionalDependencies;
    private List<String> inConflictWith;
    private List<String> replaces;
    private Size downloadSize;
    private Size installSize;
    private String maintainer;
    private String creationDateTime;
    private String validatedBy;

    // Campos específicos de AUR
    private String aurUrl; // URL de AUR
    private String firstSubmitted; // Primera vez subido
    private List<String> keywords; // Palabras clave
    private String lastModified; // Última vez modificado
    private double maintainerPopularity; // Popularidad
    private int votes; // Votos
    private boolean outOfDate; // Desactualizado
    private List<String> buildDependencies; // Dependencias de compilación
    private List<String> checkDependencies; // Dependencias de verificación

    // Métodos setters personalizados para procesar cadenas de texto en listas
    public void setLicences(String licences) {
        this.licences = Arrays.stream(licences.split("\\s+"))
                .map(String::trim)
                .toList();
    }

    public void setGroups(String groups) {
        this.groups = Arrays.stream(groups.split("\\s+"))
                .map(String::trim)
                .toList();
    }

    public void setProvides(String provides) {
        this.provides = Arrays.stream(provides.split("\\s+"))
                .map(String::trim)
                .toList();
    }

    public void setDepends(String depends) {
        this.depends = Arrays.stream(depends.split("\\s+"))
                .map(String::trim)
                .toList();
    }

    public void setOptionalDependencies(String optionalDependencies) {
        this.optionalDependencies = Arrays.stream(optionalDependencies.split("\\n"))
                .map(String::trim)
                .toList();
    }

    public void setInConflictWith(String inConflictWith) {
        this.inConflictWith = Arrays.stream(inConflictWith.split("\\s+"))
                .map(String::trim)
                .toList();
    }

    public void setReplaces(String replaces) {
        this.replaces = Arrays.stream(replaces.split("\\s+"))
                .map(String::trim)
                .toList();
    }

    public void setKeywords(String keywords) {
        this.keywords = Arrays.stream(keywords.split("\\s+"))
                .map(String::trim)
                .toList();
    }

    public void setBuildDependencies(String keywords) {
        this.buildDependencies = Arrays.stream(keywords.split("\\s+"))
                .map(String::trim)
                .toList();
    }

    public void setCheckDependencies(String keywords) {
        this.checkDependencies = Arrays.stream(keywords.split("\\s+"))
                .map(String::trim)
                .toList();
    }
}
