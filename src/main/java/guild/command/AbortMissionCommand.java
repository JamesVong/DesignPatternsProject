package main.java.guild.command;

public class AbortMissionCommand implements Command {
    private final MockBountyHunter hunter;

    public AbortMissionCommand(MockBountyHunter hunter) {
        this.hunter = hunter;
    }

    @Override
    public void execute() {
        hunter.abort();
    }
}
