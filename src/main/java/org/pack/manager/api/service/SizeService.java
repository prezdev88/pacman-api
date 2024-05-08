package org.pack.manager.api.service;

import org.pack.manager.api.model.Size;

import java.util.HashMap;

public interface SizeService {
    Size map(HashMap<Integer, String> packageHashMap, int index);
}
