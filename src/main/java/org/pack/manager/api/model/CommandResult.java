package org.pack.manager.api.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommandResult {

    private int exitCode;
    private List<String> output;

    public boolean isNotSuccess() {
        return !isSuccess();
    }

    public boolean isSuccess() {
        return exitCode == 0;
    }
}
