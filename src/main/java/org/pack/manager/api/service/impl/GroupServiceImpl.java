package org.pack.manager.api.service.impl;

import lombok.AllArgsConstructor;
import org.pack.manager.api.mapper.GroupMapper;
import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final CommandRunner commandRunner;
    private final GroupMapper groupMapper;

    @Override
    public List<String> getGroupNames() {
        CommandRequest commandRequest = new CommandRequest("pacman -Sg");
        CommandResult commandResult = commandRunner.exec(commandRequest);

        List<String> output = commandResult.getOutput();
        Collections.sort(output);

        return output;
    }

    @Override
    public List<String> getPackageNamesBy(String groupName) {
        CommandRequest commandRequest = new CommandRequest("pacman -Sg " + groupName);
        CommandResult commandResult = commandRunner.exec(commandRequest);

        List<String> output = commandResult.getOutput();

        return groupMapper.mapToPackageNames(output);
    }
}
