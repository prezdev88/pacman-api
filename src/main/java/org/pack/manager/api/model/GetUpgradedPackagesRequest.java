package org.pack.manager.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUpgradedPackagesRequest {
    @Schema(description = "Year to filter the upgraded packages", example = "2024")
    private int year;

    @Schema(description = "Month to filter the upgraded packages", example = "2")
    private int month;
}
