package guild.chain;

public class MidTierHunterHandler extends HunterAssignmentHandler {
    @Override
    public void assignHunter(MockMission mission) {
        if (mission.getThreatLevel() == 2) {
            System.out.println("MidTierHunter assigned to mission: " + mission.getMissionId());
        } else if (next != null) {
            next.assignHunter(mission);
        } else {
            System.out.println("No available hunter for mission: " + mission.getMissionId());
        }
    }
}
