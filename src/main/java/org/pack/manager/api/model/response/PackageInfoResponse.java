package org.pack.manager.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.pack.manager.api.model.PackageInfo;

@Getter
@Setter
@AllArgsConstructor
public class PackageInfoResponse {
    private PackageInfo pack;
}
