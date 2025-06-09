package guild.scheduler;

import guild.bounty.BountyHunter;
import guild.availability.AvailabilityState;
import guild.availability.AvailableState;
import guild.chain.*;
import guild.facade.MissionFacade;
import guild.observer.AvailabilityObserver;
import guild.criminal.Criminal;
import guild.singleton.GuildRegistry;

import java.util.*;

public class MissionScheduler implements AvailabilityObserver {
    private PriorityQueue<Mission> missionQueue;
    private List<BountyHunter> registeredHunters;
    private final Queue<MockMission> pendingMissions = new LinkedList<>();
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

    public void addMission(Criminal criminal, int priority) {
        Map<String, BountyHunter> allHunters = GuildRegistry.getInstance().getAllHunters();

        // Chain of Responsibility setup
        HunterAssignmentHandler low = new LowTierHunterHandler();
        HunterAssignmentHandler mid = new MidTierHunterHandler();
        HunterAssignmentHandler high = new HighTierHunterHandler();
        low.setNext(mid);
        mid.setNext(high);

        // Build mission
        String missionID = generateMissionID();
        MockMission mission = new MockMission(missionID, priority, allHunters.values(), criminal);
        low.assignHunter(mission);

        if (mission.getAssignedHunter() == null) {
            System.out.println("No suitable hunter available. Queuing mission...");
            pendingMissions.add(mission);
        } else {
            System.out.println("Assigned Hunter: " + mission.getAssignedHunter().getName());

            // Use processMission from facade
            MissionFacade facade = new MissionFacade("resources/criminals.csv"); // <-- use correct path
            facade.processMission(mission.getAssignedHunter(), mission.getCriminal(), priority);
        }
    }

    private String generateMissionID() {
        return "M" + String.format("%03d", missionCounter++);
    }

    public Queue<MockMission> getPendingMissions() {
        return pendingMissions;
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

        // Start the mission via Mission Manager
//        guild.mission.MissionManager missionManager = new guild.mission.MissionManager();
//        missionManager.executeMissionWithStates(hunter, mission.getTarget());
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

    public List<BountyHunter> getRegisteredHunters() {
        return registeredHunters;
    }
}