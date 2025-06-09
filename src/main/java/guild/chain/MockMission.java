package guild.chain;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockMission {
    private String missionId;
    private int priority;
    private List<BountyHunter> availableHunters;
    private BountyHunter assignedHunter;
    private Criminal criminal;

    public MockMission(String missionId, int priority, Collection<BountyHunter> availableHunters, Criminal criminal) {
        this.missionId = missionId;
        this.priority = priority;
        this.availableHunters = new ArrayList<>(availableHunters);
        this.criminal = criminal;
    }

    public String getMissionId() {
        return missionId;
    }

    public int getPriority() {
        return priority;
    }

    public Criminal getCriminal() {
        return criminal;
    }

    public void setAssignedHunter(BountyHunter hunter) {
        this.assignedHunter = hunter;
    }

    public List<BountyHunter> getAvailableHunters() {
        return availableHunters;
    }


    public BountyHunter getAssignedHunter() {
        return assignedHunter;
    }
}
