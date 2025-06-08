package main.java.guild.chain;

public class LowTierHunterHandler extends HunterAssignmentHandler {
    @Override
    public void assignHunter(MockMission mission) {
        if (mission.getThreatLevel() == 1) {
            System.out.println("LowTierHunter assigned to mission: " + mission.getMissionId());
        } else if (next != null) {
            next.assignHunter(mission);
        } else {
            System.out.println("No available hunter for mission: " + mission.getMissionId());
        }
    }
}
