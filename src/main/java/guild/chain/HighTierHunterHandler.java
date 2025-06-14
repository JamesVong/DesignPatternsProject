package guild.chain;

import guild.availability.UnavailableState;
import guild.bounty.BountyHunter;

public class HighTierHunterHandler extends HunterAssignmentHandler {

    @Override
    public void assignHunter(MockMission mission) {
        for (BountyHunter hunter : mission.getAvailableHunters()) {
            if (canHandle(hunter, mission.getPriority()) &&
                    hunter.isAvailable()) {

                mission.setAssignedHunter(hunter);
                hunter.setAvailability(new UnavailableState());

                System.out.println("[HighTierHunterHandler] Assigned Hunter: " + hunter.getName() +
                        " → Mission: " + mission.getMissionId());
                return;
            }
        }

        if (next != null) {
            next.assignHunter(mission);
        } else {
            System.out.println("[HighTierHunterHandler] No suitable hunter found for mission: " +
                    mission.getMissionId());
        }
    }

    private boolean canHandle(BountyHunter hunter, int threatLevel) {
        String rank = hunter.getRank();
        return "Commander".equalsIgnoreCase(rank) && threatLevel >= 7;
    }
}
