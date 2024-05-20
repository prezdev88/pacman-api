package org.pack.manager.api.service.impl;

import org.pack.manager.api.service.UtilService;
import org.springframework.stereotype.Service;

@Service
public class UtilServiceImpl implements UtilService {
    @Override
    public String clear(String packageName) {
        packageName = packageName.split(">")[0].trim();
        packageName = packageName.split("=")[0].trim();

        return packageName;
    }
}
