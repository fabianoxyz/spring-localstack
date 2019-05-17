package xyz.fabiano.spring.localstack.legacy.command;

import java.util.Arrays;

public class PullCommand extends Command {

    private static final int PULL_COMMAND_TIMEOUT_MINUTES = 10;

    private final String imageName;

    public PullCommand(String imageName) {
        this.imageName = imageName;
    }


    public void execute() {
        dockerExe.execute(Arrays.asList("pull", imageName), PULL_COMMAND_TIMEOUT_MINUTES);
    }
}
