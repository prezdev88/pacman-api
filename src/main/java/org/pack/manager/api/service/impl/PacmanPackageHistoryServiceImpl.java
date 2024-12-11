package org.pack.manager.api.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pack.manager.api.mapper.PackageHistoryMapper;
import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.model.PackageHistory;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.PackageHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PacmanPackageHistoryServiceImpl implements PackageHistoryService {

    private final CommandRunner commandRunner;
    private final PackageHistoryMapper packageHistoryMapper;

    @Override
    public List<PackageHistory> getPackageHistory(String packageName) {
        String commandTemplate = "grep '\\[ALPM\\]' /var/log/pacman.log | grep ' %s '";
        String formattedCommand = String.format(commandTemplate, packageName);

        CommandRequest commandRequest = new CommandRequest(formattedCommand);
        CommandResult commandResult = commandRunner.exec(commandRequest);

        return packageHistoryMapper.map(commandResult.getOutput());
    }
}
