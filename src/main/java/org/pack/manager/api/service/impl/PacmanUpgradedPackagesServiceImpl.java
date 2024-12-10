package org.pack.manager.api.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pack.manager.api.mapper.UpgradedPackagesMapper;
import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.model.GetUpgradedPackagesRequest;
import org.pack.manager.api.model.UpgradedPackage;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.UpgradedPackagesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PacmanUpgradedPackagesServiceImpl implements UpgradedPackagesService {

    private final CommandRunner commandRunner;
    private final UpgradedPackagesMapper pacmanUpgradedPackagesMapperImpl;

    @Override
    public List<UpgradedPackage> getUpgradedPackages(GetUpgradedPackagesRequest getUpgradedPackagesRequest) {
        int month = getUpgradedPackagesRequest.getMonth();
        int year = getUpgradedPackagesRequest.getYear();

        String commandTemplate = "grep 'upgraded' /var/log/pacman.log | grep '\\[%s-%s'";
        String monthString = (month < 10 ? "0" + month : String.valueOf(month));
        String formattedCommand = String.format(commandTemplate, year, monthString);

        CommandRequest commandRequest = new CommandRequest(formattedCommand);
        CommandResult commandResult = commandRunner.exec(commandRequest);

        return pacmanUpgradedPackagesMapperImpl.map(commandResult.getOutput());
    }
}
