package org.pack.manager.api.service.impl;

import lombok.AllArgsConstructor;
import org.pack.manager.api.exception.PackageNotFoundException;
import org.pack.manager.api.mapper.SearchPackageMapper;
import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.model.InstalledPackage;
import org.pack.manager.api.model.SearchPackage;
import org.pack.manager.api.service.CommandRunner;
import org.pack.manager.api.service.InstalledPackageService;
import org.pack.manager.api.service.SearchPackageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchPackageServiceImpl implements SearchPackageService {

    private final CommandRunner commandRunner;
    private final SearchPackageMapper searchPackageMapper;
    private final InstalledPackageService installedPackageService;

    @Override
    public List<SearchPackage> search(String name) {
        try {
            String command = String.format("""
                    yay -Ss %s | grep -E "^[^[:space:]]*%s[^[:space:]]*"
                    """, name, name);

            CommandResult commandResult = commandRunner.exec(new CommandRequest(command));
            List<String> output = commandResult.getOutput();
            List<SearchPackage> searchPackages = searchPackageMapper.map(output);

            return flagInstalledApps(searchPackages);
        } catch (PackageNotFoundException packageNotFoundException) {
            throw new PackageNotFoundException("Package '" + name + "' not found");
        }
    }

    private List<SearchPackage> flagInstalledApps(List<SearchPackage> searchPackages) {
        List<InstalledPackage> installedPackages = installedPackageService.getInstalledPackages();

        // Obtener la lista de nombres de paquetes instalados en un Set para búsqueda rápida
        Set<String> installedPackageNames = installedPackages.stream()
                .map(InstalledPackage::getName)
                .collect(Collectors.toSet());

        // Recorrer las apps encontradas y marcar si están instaladas
        searchPackages.forEach(searchPackage -> {
            boolean isInstalled = installedPackageNames.contains(searchPackage.getName());
            searchPackage.setInstalled(isInstalled);
        });

        return searchPackages;
    }
}
