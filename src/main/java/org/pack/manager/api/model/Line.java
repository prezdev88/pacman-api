package org.pack.manager.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Line {
    private int index;
    private String value;
    private boolean increaseIndex;
}
