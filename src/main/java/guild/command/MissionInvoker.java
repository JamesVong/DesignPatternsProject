package main.java.guild.command;

import java.util.LinkedList;
import java.util.Queue;

public class MissionInvoker {
    private final Queue<Command> commandQueue = new LinkedList<>();

    public void addCommand(Command command) {
        commandQueue.offer(command);
    }

    public void executeAll() {
        while (!commandQueue.isEmpty()) {
            commandQueue.poll().execute();
        }
    }
}
