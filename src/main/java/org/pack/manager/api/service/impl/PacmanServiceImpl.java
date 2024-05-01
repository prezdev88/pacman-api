package org.pack.manager.api.service.impl;

import java.util.List;

import org.pack.manager.api.mapper.PackageMapper;
import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.model.UpgradePackage;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.PackageService;
import org.springframework.stereotype.Service;
import org.pack.manager.api.model.Package;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PacmanServiceImpl implements PackageService {

    private final CommandRunner commandRunner;
    private final PackageMapper packageMapper;

    @Override
    public List<Package> getInstalledPackages() {
        CommandRequest commandRequest = new CommandRequest();

        commandRequest.setCommand("pacman -Qnei");

        CommandResult commandResult = commandRunner.exec(commandRequest);

        List<String> output = commandResult.getOutput();

        return packageMapper.map(output);
    }

    @Override
    public List<UpgradePackage> getUpgradePackages() {
        throw new UnsupportedOperationException("Unimplemented method 'getUpgradePackages'");
    }

    @Override
    public Package getPackageBy(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getPackageBy'");
    }

}
