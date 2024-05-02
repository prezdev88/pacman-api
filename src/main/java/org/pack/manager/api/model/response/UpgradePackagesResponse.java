package org.pack.manager.api.model.response;

import lombok.Getter;
import lombok.Setter;
import org.pack.manager.api.model.UpgradePackage;

import java.util.List;

@Getter
@Setter
public class UpgradePackagesResponse {
    private List<UpgradePackage> packages;
}
