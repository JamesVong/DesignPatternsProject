package main.java.guild.strategy;

import main.java.guild.bounty.BountyHunter;
import main.java.guild.criminal.Criminal;

public interface CaptureStrategy {
    BountyHunter equipHunter(BountyHunter hunter, Criminal criminal);

    void executeCapture(BountyHunter equippedHunter, Criminal criminal);
}