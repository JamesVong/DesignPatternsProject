package guild.command;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

public class CaptureCommand implements Command {
    private BountyHunter hunter;
    private Criminal criminal;

    public CaptureCommand(BountyHunter hunter, Criminal criminal) {
        this.hunter = hunter;
        this.criminal = criminal;
    }

    @Override
    public void execute() {
        hunter.capture(criminal);
    }
}
