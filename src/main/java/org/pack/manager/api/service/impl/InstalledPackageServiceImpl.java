package org.pack.manager.api.service.impl;

import lombok.AllArgsConstructor;
import org.pack.manager.api.mapper.InstalledPackageMapper;
import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.model.InstalledPackage;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.InstalledPackageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstalledPackageServiceImpl implements InstalledPackageService {

    private final CommandRunner commandRunner;
    private final InstalledPackageMapper installedPackageMapper;

    @Override
    public List<InstalledPackage> getInstalledPackages() {
        CommandResult exec = commandRunner.exec(new CommandRequest("yay -Q"));
        return installedPackageMapper.map(exec.getOutput());
    }
}
