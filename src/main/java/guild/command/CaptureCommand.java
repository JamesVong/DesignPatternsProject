package main.java.guild.command;

public class CaptureCommand implements Command {
    private final MockBountyHunter hunter;

    public CaptureCommand(MockBountyHunter hunter) {
        this.hunter = hunter;
    }

    @Override
    public void execute() {
        hunter.capture();
    }
}
