package org.pack.manager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
public class UpdatedAt {

    private String date;
    private String hour;
    private ZoneOffset offset;

    public UpdatedAt(OffsetDateTime dateTime) {
        this.date = dateTime.toLocalDate().toString();
        this.hour = dateTime.toLocalTime().toString();
        this.offset = dateTime.getOffset();
    }
}
