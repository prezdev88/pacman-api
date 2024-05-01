package org.pack.manager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandRequest {

    private String command;
    private String rootPassword;

    public CommandRequest(String command) {
        this.command = command;
    }

    public CommandRequest(String command, String rootPassword) {
        this.command = command;
        this.rootPassword = rootPassword;
    }

    public String getCommand() {
        if (isSudoCommand()) {
            command = "echo " + rootPassword + " | sudo -S " + command;
        }

        return command;
    }

    public boolean isSudoCommand() {
        return rootPassword != null;
    }

}
