package org.pack.manager.api.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.pack.manager.api.exception.PackageNotFoundException;
import org.pack.manager.api.exception.WrongPasswordException;
import org.pack.manager.api.mapper.LitePackageMapper;
import org.pack.manager.api.mapper.PackageMapper;
import org.pack.manager.api.mapper.UpgradePackageMapper;
import org.pack.manager.api.model.*;
import org.pack.manager.api.model.Package;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.PackageService;
import org.pack.manager.api.service.UtilService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Slf4j
@Service
@AllArgsConstructor
public class NativePackageServiceImpl implements PackageService {

    private final CommandRunner commandRunner;
    private final PackageMapper packageMapper;
    private final UpgradePackageMapper upgradePackageMapper;
    private final LitePackageMapper litePackageMapper;
    private final UtilService utilService;

    @Override
    public List<Package> getExplicitInstalledPackages() {
        CommandRequest commandRequest = new CommandRequest("pacman -Qnei");
        CommandResult commandResult = commandRunner.exec(commandRequest);

        List<String> output = commandResult.getOutput();

        return packageMapper.map(output);
    }

    @Override
    public List<LitePackage> getExplicitLiteInstalledPackages() {
        CommandRequest commandRequest = new CommandRequest("pacman -Qne");
        CommandResult commandResult = commandRunner.exec(commandRequest);

        List<String> output = commandResult.getOutput();

        return litePackageMapper.map(output);
    }

    @Override
    public List<UpgradePackage> getUpgradePackages() {
        CommandRequest commandRequest = new CommandRequest("pacman -Qu");
        CommandResult commandResult = commandRunner.exec(commandRequest);

        List<String> output = commandResult.getOutput();

        return upgradePackageMapper.map(output);
    }

    @Override
    public Package getPackageBy(String name) {
        try {
            name = utilService.clear(name);
            CommandRequest commandRequest = new CommandRequest("pacman -Qni " + name);
            CommandResult commandResult = commandRunner.exec(commandRequest);

            List<String> output = commandResult.getOutput();

            return packageMapper.mapToOne(output);
        } catch (PackageNotFoundException ex) {
            throw new PackageNotFoundException("Package '" + name + "' not found");
        }
    }

}
