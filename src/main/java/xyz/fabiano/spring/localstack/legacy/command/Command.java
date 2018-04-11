package xyz.fabiano.spring.localstack.legacy.command;

import xyz.fabiano.spring.localstack.legacy.DockerExe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {

    protected final DockerExe dockerExe = new DockerExe();

    protected List<String> options = new ArrayList<>();

    protected void addOptions(String... items) {
        options.addAll(Arrays.asList(items));
    }
}
