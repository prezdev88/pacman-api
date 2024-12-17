package org.pack.manager.api.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.pack.manager.api.model.SearchPackage;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchPackageResponse {
    private List<SearchPackage> packages;
}
