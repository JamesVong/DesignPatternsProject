package main.java.guild.context;

import main.java.guild.bounty.BountyHunter;
import main.java.guild.criminal.Criminal;
import main.java.guild.strategy.CaptureStrategy;
import main.java.guild.strategy.StrategySelector;

public class CaptureContext {
    private CaptureStrategy strategy;

    public void executeMission(BountyHunter hunter, Criminal criminal) {
        System.out.println("\n Mission Briefing");
        System.out.println("Hunter: " + hunter.getName() + " (" + hunter.getFaction() + ")");
        System.out.println("Target: " + criminal.getAlias() + " (Threat Level: " + criminal.getThreatLevel() + ")");

        strategy = StrategySelector.selectStrategy(criminal);
        BountyHunter equippedHunter = strategy.equipHunter(hunter, criminal);

        System.out.println("\nMission Execution");
        strategy.executeCapture(equippedHunter, criminal);

        System.out.println("\n Post Mission Report");
        equippedHunter.reportStatus();
    }

    public BountyHunter equipHunterOnly(BountyHunter hunter, Criminal criminal) {
        System.out.println("Equipment Briefing");
        System.out.println("Hunter: " + hunter.getName() + " (" + hunter.getFaction() + ")");
        System.out.println("Target: " + criminal.getAlias() + " (Threat Level: " + criminal.getThreatLevel() + ")");

        strategy = StrategySelector.selectStrategy(criminal);
        return strategy.equipHunter(hunter, criminal);
    }
}
