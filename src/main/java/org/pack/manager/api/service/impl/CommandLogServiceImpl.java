package org.pack.manager.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pack.manager.api.service.CommandLogService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommandLogServiceImpl implements CommandLogService {

    private final String X_EMOJI = "\uD83D\uDDF7";
    private final String CHECK_EMOJI = "\uD83D\uDDF9";

    @Override
    public void showComandNotExecutedLog(String command) {
        showCommandLog(command, X_EMOJI);
    }

    @Override
    public void showComandExecutedLog(String command) {
        showCommandLog(command, CHECK_EMOJI);
    }

    public void showCommandLog(String command, String emoji){
        log.info(">> {} [{}]", emoji, command);
    }
}
