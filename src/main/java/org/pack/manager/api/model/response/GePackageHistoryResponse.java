package org.pack.manager.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.pack.manager.api.model.PackageHistory;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GePackageHistoryResponse {
    private List<PackageHistory> history;
}
