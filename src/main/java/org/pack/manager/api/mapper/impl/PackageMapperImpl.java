package org.pack.manager.api.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pack.manager.api.mapper.PackageMapper;
import org.pack.manager.api.model.Package;
import org.pack.manager.api.model.Size;
import org.springframework.stereotype.Service;

@Service
public class PackageMapperImpl implements PackageMapper {

    @Override
    public List<Package> map(List<String> output) {
        int index = 0;
        List<Package> packages = new ArrayList<>();
        HashMap<Integer, String> hashMap = new HashMap<>();

        for (String line : output) {
            if (line.equals("")) {
                Package pack = this.map(hashMap);
                packages.add(pack);

                index = 0;
                hashMap = new HashMap<>();
                continue;
            }

            try {
                // String name = line.split(" : ")[0].trim();
                String value = line.split(" : ")[1].trim();

                hashMap.put(index, value);
            } catch (ArrayIndexOutOfBoundsException e) {
                index--;
                String pastValue = hashMap.get(index);
                String value = pastValue + "\n" + line.trim();

                hashMap.put(index, value);
            }
            index++;
        }

        return packages;
    }

    @Override
    public Package map(HashMap<Integer, String> packageHashMap) {
        Package pack = new Package();

        pack.setName(packageHashMap.get(0));
        pack.setVersion(packageHashMap.get(1));
        pack.setDescription(packageHashMap.get(2));
        pack.setArchitecture(packageHashMap.get(3));
        pack.setUrl(packageHashMap.get(4));
        pack.setLicences(packageHashMap.get(5));
        pack.setGroups(packageHashMap.get(6));
        pack.setProvides(packageHashMap.get(7));
        pack.setDepends(packageHashMap.get(8));
        pack.setOptionalDependencies(packageHashMap.get(9));
        pack.setRequestedBy(packageHashMap.get(10));
        pack.setOptionalFor(packageHashMap.get(11));
        pack.setInConflictWith(packageHashMap.get(12));
        pack.setReplaces(packageHashMap.get(13));

        try {
            Size size = new Size();

            size.setValue(Float.parseFloat(packageHashMap.get(14).replaceAll(",", ".").split(" ")[0]));
            size.setUnit(packageHashMap.get(14).split(" ")[1]);

            pack.setSize(size);
        } catch (NumberFormatException | NullPointerException e) {
            pack.setSize(null);
        }
        pack.setManager(packageHashMap.get(15));
        pack.setCreationDateTime(packageHashMap.get(16));
        pack.setInstallDateTime(packageHashMap.get(17));
        pack.setReasonInstallation(packageHashMap.get(18));
        pack.setInstallationScript(packageHashMap.get(19));
        pack.setValidatedBy(packageHashMap.get(20));

        return pack;
    }

}
