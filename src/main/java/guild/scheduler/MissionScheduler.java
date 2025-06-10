package guild.scheduler;

import guild.availability.AvailabilityState;
import guild.availability.AvailableState;
import guild.bounty.BountyHunter;
import guild.chain.*;
import guild.criminal.Criminal;
import guild.facade.MissionFacade;
import guild.observer.AvailabilityObserver;
import guild.singleton.GuildRegistry;

import java.util.*;

public class MissionScheduler implements AvailabilityObserver {
    private final PriorityQueue<Mission> missionQueue;
    private static MissionScheduler instance;
    private final Queue<MockMission> pendingMissions = new LinkedList<>();
    private int missionCounter = 1;

    public MissionScheduler() {
        this.missionQueue = new PriorityQueue<>(Comparator.comparingInt(Mission::getPriority).reversed());
        System.out.println("[SCHEDULER] Mission Scheduler initialized");
    }



    public static MissionScheduler getInstance() {
        if (instance == null) {
            instance = new MissionScheduler();
        }
        return instance;
    }

    public void addMission(Criminal criminal, int priority) {
        List<BountyHunter> availableHunters = GuildRegistry.getInstance().getAvailableHunters();

        // Chain of Responsibility
        HunterAssignmentHandler low = new LowTierHunterHandler();
        HunterAssignmentHandler mid = new MidTierHunterHandler();
        HunterAssignmentHandler high = new HighTierHunterHandler();
        low.setNext(mid);
        mid.setNext(high);

        String missionID = generateMissionID();
        MockMission mission = new MockMission(missionID, priority, availableHunters, criminal);
        low.assignHunter(mission);

        if (mission.getAssignedHunter() == null) {
            System.out.println("No suitable hunter available. Queuing mission...");
            pendingMissions.add(mission);
        } else {
            System.out.println("Assigned Hunter: " + mission.getAssignedHunter().getName());
            new MissionFacade("src/main/java/resources/criminals.csv")
                    .processMission(mission.getMissionId(), mission.getAssignedHunter(), mission.getCriminal(), priority);
        }
    }

    private String generateMissionID() {
        return "M" + String.format("%03d", missionCounter++);
    }

    public void registerAsObserver(BountyHunter hunter) {
        hunter.addObserver(this);
        System.out.println("[SCHEDULER] " + hunter.getName() + " registered with scheduler");
    }


    @Override
    public void onAvailabilityChange(BountyHunter hunter, AvailabilityState oldState, AvailabilityState newState) {
        System.out.println("[SCHEDULER] Received notification: " + hunter.getName() + " is now " + newState.getStatus());
        if (newState instanceof AvailableState) {
            System.out.println("[SCHEDULER] Hunter became available - checking for pending missions...");
            tryAssignMissions();
        }
    }


    private void tryAssignMissions() {
        Iterator<MockMission> iterator = pendingMissions.iterator();

        while (iterator.hasNext()) {
            MockMission oldMission = iterator.next();
            List<BountyHunter> availableHunters = GuildRegistry.getInstance().getAvailableHunters();

            if (availableHunters.isEmpty()) {
                break; // No one available to handle any mission right now
            }

            // Chain of Responsibility Setup
            HunterAssignmentHandler low = new LowTierHunterHandler();
            HunterAssignmentHandler mid = new MidTierHunterHandler();
            HunterAssignmentHandler high = new HighTierHunterHandler();
            low.setNext(mid);
            mid.setNext(high);

            // Create new mission object with updated hunter pool
            MockMission newMission = new MockMission(
                    oldMission.getMissionId(),
                    oldMission.getPriority(),
                    availableHunters,
                    oldMission.getCriminal()
            );

            // Try to assign
            low.assignHunter(newMission);

            if (newMission.getAssignedHunter() != null) {
                iterator.remove(); // Remove old mission from queue

                System.out.println("Assigned Hunter: " + newMission.getAssignedHunter().getName());

                new MissionFacade("src/main/java/resources/criminals.csv")
                        .processMission(
                                newMission.getMissionId(),
                                newMission.getAssignedHunter(),
                                newMission.getCriminal(),
                                newMission.getPriority()
                        );
            }
        }
    }


    private BountyHunter findAvailableHunter() {
        for (BountyHunter hunter : GuildRegistry.getInstance().getAllHunters().values()) {
            if (hunter.isAvailable()) return hunter;
        }
        return null;
    }

    public void displayStatus() {
        Map<String, BountyHunter> hunters = GuildRegistry.getInstance().getAllHunters();
        System.out.println("\n[SCHEDULER STATUS]");
        System.out.println("Pending missions: " + pendingMissions.size());
        System.out.println("Registered hunters: " + hunters.size());

        System.out.println("\nHunter Availability:");
        for (BountyHunter hunter : hunters.values()) {
            System.out.println("  • " + hunter.getName() + ": " + hunter.getAvailabilityStatus() +
                    " (" + hunter.getAvailabilityState().getDescription() + ")");
        }

        if (!pendingMissions.isEmpty()) {
            System.out.println("\nPending Missions:");
            for (MockMission mission : pendingMissions) {
                System.out.println("  • " + mission);
            }
        }
        System.out.println();
    }

    public Queue<MockMission> getPendingMissions() {
        return pendingMissions;
    }
}
