package org.pack.manager.api.service;

import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;

public interface CommandRunner {
    CommandResult exec(CommandRequest commandRequest);
}
