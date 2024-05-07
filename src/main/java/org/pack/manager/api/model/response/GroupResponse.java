package org.pack.manager.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponse {
    private List<String> groupNames;
}
