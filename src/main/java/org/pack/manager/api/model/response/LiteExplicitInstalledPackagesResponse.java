package org.pack.manager.api.model.response;

import lombok.Getter;
import lombok.Setter;
import org.pack.manager.api.model.LitePackage;

import java.util.List;

@Getter
@Setter
public class LiteExplicitInstalledPackagesResponse {
    private List<LitePackage> packages;
}
