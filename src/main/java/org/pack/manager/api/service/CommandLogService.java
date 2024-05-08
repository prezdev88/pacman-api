package org.pack.manager.api.service;

public interface CommandLogService {
    void showComandNotExecutedLog(String command);

    void showComandExecutedLog(String command);
}
