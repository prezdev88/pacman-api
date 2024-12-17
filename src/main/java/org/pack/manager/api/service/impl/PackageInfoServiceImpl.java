package org.pack.manager.api.service.impl;

import lombok.AllArgsConstructor;
import org.pack.manager.api.exception.PackageNotFoundException;
import org.pack.manager.api.mapper.PackageInfoMapper;
import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.model.PackageInfo;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.PackageInfoService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageInfoServiceImpl implements PackageInfoService {

    private final CommandRunner commandRunner;
    private final PackageInfoMapper packageInfoMapper;

    @Override
    public PackageInfo get(String name) {
        try {
            String command = String.format("yay -Si %s", name);

            CommandResult commandResult = commandRunner.exec(new CommandRequest(command));

            return packageInfoMapper.mapToOne(commandResult.getOutput());
        } catch (PackageNotFoundException ex) {
            throw new PackageNotFoundException("Package '" + name + "' not found");
        }
    }
}
