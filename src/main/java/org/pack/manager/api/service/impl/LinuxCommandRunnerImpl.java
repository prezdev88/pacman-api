package org.pack.manager.api.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pack.manager.api.model.CommandRequest;
import org.pack.manager.api.model.CommandResult;
import org.pack.manager.api.service.CommandLogService;
import org.pack.manager.api.service.CommandRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LinuxCommandRunnerImpl implements CommandRunner {

    private final CommandLogService commandLogService;

    @Override
    public CommandResult exec(CommandRequest commandRequest) {
        try {
            String command = commandRequest.getCommand();
            String[] stringCommand = new String[]{"bash", "-c", command};

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(stringCommand);

            List<String> output = readOutput(process);
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                output = read(process.getErrorStream());
                commandLogService.showComandNotExecutedLog(command);
            } else {
                commandLogService.showComandExecutedLog(command);
            }

            return new CommandResult(exitCode, output);
        } catch (Exception e) {
            e.printStackTrace();
            commandLogService.showComandNotExecutedLog(commandRequest.getCommand());
            return new CommandResult(-1, new ArrayList<>());
        }
    }


    private List<String> readOutput(Process process) throws IOException {
        return read(process.getInputStream());
    }

    private List<String> read(InputStream inputStream) throws IOException {
        List<String> output = new ArrayList<>();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            output.add(line);
        }

        return output;
    }

}
