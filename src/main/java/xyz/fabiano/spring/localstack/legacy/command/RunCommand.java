package xyz.fabiano.spring.localstack.legacy.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RunCommand extends Command {

    private final String imageName;

    public RunCommand(String imageName) {
        this.imageName = imageName;
    }

    public String execute() {
        List<String> args = new ArrayList<>();
        args.add("run");
        args.add("-d");
        args.addAll(options);
        args.add(imageName);

        return dockerExe.execute(args);
    }

    public xyz.fabiano.spring.localstack.legacy.command.RunCommand withExposedPorts(String portsToExpose, boolean randomize) {
        String portsOption = String.format("%s:%s", randomize ? "" : portsToExpose, portsToExpose);
        addOptions("-p", portsOption);
        return this;
    }

    public xyz.fabiano.spring.localstack.legacy.command.RunCommand withEnvironmentVariable(String name, String value) {
        addEnvOption(name, value);
        return this;
    }

    public xyz.fabiano.spring.localstack.legacy.command.RunCommand withEnvironmentVariables(Map<String, String> environmentVariables) {
        environmentVariables.forEach((name, value) -> addEnvOption(name, value));
        return this;
    }

    private void addEnvOption(String name, String value) {
        addOptions("-e", String.format("%s=%s", name, value));
    }

}
