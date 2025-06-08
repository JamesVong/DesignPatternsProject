package guild.strategy;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

public interface CaptureStrategy {
    BountyHunter equipHunter(BountyHunter hunter, Criminal criminal);

    void executeCapture(BountyHunter equippedHunter, Criminal criminal);
}