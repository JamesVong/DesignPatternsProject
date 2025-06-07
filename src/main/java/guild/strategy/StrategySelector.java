package guild.strategy;

import guild.criminal.Criminal;

public class StrategySelector {

    public static CaptureStrategy selectStrategy(Criminal criminal) {
        int threatLevel = criminal.getThreatLevel();

        if (threatLevel >= 1 && threatLevel <= 3) {
            return new LowThreatStrategy();
        } else if (threatLevel >= 4 && threatLevel <= 6) {
            return new MediumThreatStrategy();
        } else if (threatLevel >= 7 && threatLevel <= 10) {
            return new HighThreatStrategy();
        } else {
            System.out.println("Invalid threat level: " + threatLevel + " - defaulting to medium threat");
            return new MediumThreatStrategy();
        }
    }
}