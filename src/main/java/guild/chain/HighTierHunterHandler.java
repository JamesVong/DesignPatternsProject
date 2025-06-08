package main.java.guild.chain;

public class HighTierHunterHandler extends HunterAssignmentHandler {
    @Override
    public void assignHunter(MockMission mission) {
        if (mission.getThreatLevel() == 3) {
            System.out.println("HighTierHunter assigned to mission: " + mission.getMissionId());
        } else if (next != null) {
            next.assignHunter(mission);
        } else {
            System.out.println("No available hunter for mission: " + mission.getMissionId());
        }
    }
}
