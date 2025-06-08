package main.java.guild.command;

public class TrackCommand implements Command {
    private final MockBountyHunter hunter;

    public TrackCommand(MockBountyHunter hunter) {
        this.hunter = hunter;
    }

    @Override
    public void execute() {
        hunter.track();
    }
}
