package guild.scheduler;

import guild.bounty.BountyHunter;
import guild.availability.AvailabilityState;
import guild.availability.AvailableState;
import guild.availability.UnavailableState;
import guild.observer.AvailabilityObserver;
import guild.criminal.Criminal;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class MissionScheduler implements AvailabilityObserver {
    private PriorityQueue<Mission> missionQueue;
    private List<BountyHunter> registeredHunters;
    private int missionCounter = 1;

    public MissionScheduler() {
        // Priority queue ordered by mission priority (higher number = higher priority)
        this.missionQueue = new PriorityQueue<>(Comparator.comparingInt(Mission::getPriority).reversed());
        this.registeredHunters = new ArrayList<>();
        System.out.println("[SCHEDULER] Mission Scheduler initialized");
    }

    public void registerHunter(BountyHunter hunter) {
        registeredHunters.add(hunter);
        hunter.addObserver(this);
        System.out.println("[SCHEDULER] " + hunter.getName() + " registered with scheduler");
    }

    public void addMission(Criminal target, int priority) {
        String missionId = "M" + String.format("%03d", missionCounter++);
        Mission mission = new Mission(missionId, target, priority);
        missionQueue.offer(mission);

        System.out.println("[SCHEDULER] New mission queued: " + mission);
        System.out.println("   Queue size: " + missionQueue.size() + " pending missions");

        // Try to assign immediately if hunters are available
        tryAssignMissions();
    }

    @Override
    public void onAvailabilityChange(BountyHunter hunter, AvailabilityState oldState, AvailabilityState newState) {
        System.out.println(
                "[SCHEDULER] Received notification: " + hunter.getName() + " is now " + newState.getStatus());

        if (newState instanceof AvailableState) {
            System.out.println("[SCHEDULER] Hunter became available - checking for pending missions...");
            tryAssignMissions();
        }
    }

    private void tryAssignMissions() {
        while (!missionQueue.isEmpty()) {
            BountyHunter availableHunter = findAvailableHunter();
            if (availableHunter != null) {
                Mission mission = missionQueue.poll();
                assignMission(availableHunter, mission);
            } else {
                System.out.println("[SCHEDULER] No available hunters - missions remain queued");
                break;
            }
        }
    }

    private BountyHunter findAvailableHunter() {
        for (BountyHunter hunter : registeredHunters) {
            if (hunter.isAvailable()) {
                return hunter;
            }
        }
        return null;
    }

    private void assignMission(BountyHunter hunter, Mission mission) {
        System.out.println("[SCHEDULER] MISSION ASSIGNED!");
        System.out.println("   Hunter: " + hunter.getName() + " (" + hunter.getFaction() + ")");
        System.out.println("   Mission: " + mission);
        System.out.println("   Target: " + mission.getTarget().getAlias() + " (Threat: "
                + mission.getTarget().getThreatLevel() + ")");

        // Start the mission via MissionManager
        guild.mission.MissionManager missionManager = new guild.mission.MissionManager();
        missionManager.executeMissionWithStates(hunter, mission.getTarget());
    }

    public void displayStatus() {
        System.out.println("\n[SCHEDULER STATUS]");
        System.out.println("Pending missions: " + missionQueue.size());
        System.out.println("Registered hunters: " + registeredHunters.size());

        System.out.println("\nHunter Availability:");
        for (BountyHunter hunter : registeredHunters) {
            System.out.println("  • " + hunter.getName() + ": " + hunter.getAvailabilityStatus() +
                    " (" + hunter.getAvailabilityState().getDescription() + ")");
        }

        if (!missionQueue.isEmpty()) {
            System.out.println("\nPending Missions:");
            Mission[] missions = missionQueue.toArray(new Mission[0]);
            for (Mission mission : missions) {
                System.out.println("  • " + mission);
            }
        }
        System.out.println();
    }
}