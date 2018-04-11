package xyz.fabiano.spring.localstack.legacy.command;

import java.util.ArrayList;
import java.util.List;

public class LogCommand extends Command {

    private final String containerId;

    public LogCommand(String containerId) {
        this.containerId = containerId;
    }


    public String execute() {
        List<String> args = new ArrayList<>();
        args.add("logs");
        args.addAll(options);
        args.add(containerId);

        return dockerExe.execute(args);
    }


    public xyz.fabiano.spring.localstack.legacy.command.LogCommand withNumberOfLines(Integer numberOfLines) {
        this.addOptions("--tail", numberOfLines.toString());
        return this;
    }
}
