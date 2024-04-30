package org.pack.manager.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.model.UpgradePackage;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.PackageService;
import org.springframework.stereotype.Service;
import org.pack.manager.api.model.Package;
import org.pack.manager.api.model.Size;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PacmanServiceImpl implements PackageService {

    private final CommandRunner commandRunner;

    @Override
    public List<Package> getInstalledPackages() {
        List<Package> packages = new ArrayList<>();

        CommandRequest commandRequest = new CommandRequest();

        commandRequest.setCommand("pacman -Qnei");

        CommandResult commandResult = commandRunner.exec(commandRequest);

        List<String> output = commandResult.getOutput();

        HashMap<Integer, String> hashMap = new HashMap<>();

        int index = 0;
        for (String line : output) {
            if (line.equals("")) {
                Package pack = toPack(hashMap);
                packages.add(pack);

                index = 0;
                hashMap = new HashMap<>();
                continue;
            }

            try {
                String name = line.split(" : ")[0].trim();
                String value = line.split(" : ")[1].trim();

                hashMap.put(index, value);
            } catch (ArrayIndexOutOfBoundsException e) {
                index--;
                String pastValue = hashMap.get(index);
                String value = pastValue + " " + line.trim();

                hashMap.put(index, value);
            }
            index++;
        }

        return packages;
    }

    private Package toPack(HashMap<Integer, String> hashMap) {
        Package pack = new Package();

        pack.setName(hashMap.get(0));
        pack.setVersion(hashMap.get(1));
        pack.setDescription(hashMap.get(2));
        pack.setArchitecture(hashMap.get(3));
        pack.setUrl(hashMap.get(4));
        pack.setLicences(hashMap.get(5));
        // pack.setGroups(hashMap.get(6));
        // pack.setProvides(hashMap.get(7));
        // pack.setDepends(hashMap.get(8));
        // pack.setOptionalDependencies(hashMap.get(9));
        // pack.setRequestedBy(hashMap.get(10));
        // pack.setOptionalFor(hashMap.get(11));
        // pack.setInConflictWith(hashMap.get(12));
        // pack.setReplaces(hashMap.get(13));

        try {
            Size size = new Size();

            size.setValue(Float.parseFloat(hashMap.get(14).replaceAll(",", ".").split(" ")[0]));
            size.setUnit(hashMap.get(14).split(" ")[1]);

            pack.setSize(size);
        } catch (NumberFormatException | NullPointerException e) {
            pack.setSize(null);
        }
        pack.setManager(hashMap.get(15));
        // pack.setCreationDateTime(hashMap.get(16));
        // pack.setInstallDateTime(hashMap.get(17));
        pack.setReasonInstallation(hashMap.get(18));
        // pack.setInstallationScript(hashMap.get(19));
        pack.setValidatedBy(hashMap.get(20));

        return pack;
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
