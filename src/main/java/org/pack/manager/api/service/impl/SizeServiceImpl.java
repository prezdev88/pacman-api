package org.pack.manager.api.service.impl;

import org.pack.manager.api.model.Size;
import org.pack.manager.api.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SizeServiceImpl implements SizeService {
    @Override
    public Size map(HashMap<Integer, String> packageHashMap, int index) {
        try {
            Size size = new Size();

            size.setValue(Float.parseFloat(packageHashMap.get(index).replace(",", ".").split(" ")[0]));
            size.setUnit(packageHashMap.get(index).split(" ")[1]);

            return size;
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }
}
