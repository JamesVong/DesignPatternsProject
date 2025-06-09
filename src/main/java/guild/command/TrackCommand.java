package guild.command;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

public class TrackCommand implements Command {
    private BountyHunter hunter;
    private Criminal criminal;

    public TrackCommand(BountyHunter hunter, Criminal criminal) {
        this.hunter = hunter;
        this.criminal = criminal;
    }

    @Override
    public void execute() {
        hunter.track(criminal);
    }
}
