package org.pack.manager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPackage {

    private String name;
    private String source;
    private boolean installed;

    public SearchPackage(String name, String source) {
        this.name = name;
        this.source = source;
    }
}
