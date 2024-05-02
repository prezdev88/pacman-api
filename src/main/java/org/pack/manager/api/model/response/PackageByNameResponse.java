package org.pack.manager.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.pack.manager.api.model.Package;

@Getter
@Setter
public class PackageByNameResponse {
    @JsonProperty("package")
    private Package pack;
}
