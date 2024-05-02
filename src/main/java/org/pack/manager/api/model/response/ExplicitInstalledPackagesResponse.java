package org.pack.manager.api.model.response;

import lombok.Getter;
import lombok.Setter;
import org.pack.manager.api.model.Package;

import java.util.List;

@Getter
@Setter
public class ExplicitInstalledPackagesResponse {
    private List<Package> packages;
}
