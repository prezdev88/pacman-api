package org.pack.manager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UpdatedAt extends Datetime {
    public UpdatedAt(OffsetDateTime dateTime) {
        super(dateTime);
    }
}
