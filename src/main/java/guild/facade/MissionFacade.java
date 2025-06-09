package guild.facade;

import guild.bounty.BountyHunter;
import guild.bounty.BountyHunterFactory;
import guild.bounty.MandalorianFactory;
import guild.bounty.ImperialFactory;
import guild.builder.ConcreteMissionBuilder;
import guild.builder.MissionBuilder;
import guild.builder.MissionDirector;
import guild.builder.MissionProfile;
import guild.command.CaptureCommand;
import guild.command.Command;
import guild.command.MissionInvoker;
import guild.command.TrackCommand;
import guild.criminal.Criminal;
import guild.decorator.GadgetDecorator;
import guild.mediator.BountyHunterColleague;
import guild.mediator.Colleague;
import guild.mediator.Drone;
import guild.mediator.MissionControl;
import guild.scheduler.MissionScheduler;
import guild.availability.AvailableState;
import guild.availability.UnavailableState;
import guild.adapter.CriminalAdapter;
import guild.database.Database;
import guild.database.DatabaseProxy;
import guild.singleton.GuildRegistry;
import guild.chain.*;
import guild.template.MissionExecutionTemplate;
import guild.template.StandardMissionExecution;


import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MissionFacade {

    private final Database criminalDatabase;
    private final CriminalAdapter criminalAdapter;
    private final MissionScheduler scheduler;
    private final BountyHunterFactory mandalorianFactory;
    private final BountyHunterFactory imperialFactory;
    private int missionCounter = 1;

    public MissionFacade(String criminalFilePath) {
        // Initialize components
        this.criminalDatabase = new DatabaseProxy(criminalFilePath);
        this.criminalAdapter = new CriminalAdapter(criminalDatabase);
        this.scheduler = new MissionScheduler();
        this.mandalorianFactory = new MandalorianFactory();
        this.imperialFactory = new ImperialFactory();
    }

    public List<Criminal> fetchCriminals() {
        return criminalAdapter.getCriminals();
    }

    public void recruitHunters() {
        BountyHunter dinDjarin = mandalorianFactory.recruitHunter("Din Djarin", "Silver");
        BountyHunter bobaFett = mandalorianFactory.recruitHunter("Boba Fett", "Gold");
        BountyHunter imperialAgent = imperialFactory.recruitHunter("Agent Kallus", "Commander");

        scheduler.registerHunter(dinDjarin);
        scheduler.registerHunter(bobaFett);
        scheduler.registerHunter(imperialAgent);
    }

    public void displaySchedulerStatus() {
        scheduler.displayStatus();
    }

//    public void addMission(Criminal criminal, int priority) {
//        Map<String, BountyHunter> allHunters = GuildRegistry.getInstance().getAllHunters();
//
//        HunterAssignmentHandler low = new LowTierHunterHandler();
//        HunterAssignmentHandler mid = new MidTierHunterHandler();
//        HunterAssignmentHandler high = new HighTierHunterHandler();
//        low.setNext(mid);
//        mid.setNext(high);
//
//        MockMission mockMission = new MockMission("M001", priority, allHunters.values());
//        low.assignHunter(mockMission);
//
//        BountyHunter assignedHunter = mockMission.getAssignedHunter();
//        if (assignedHunter == null) {
//            System.out.println("No suitable hunter available. Queuing mission...");
//            MissionScheduler scheduler = new MissionScheduler();
//            scheduler.addMission(criminal, priority);
//            return;
//        }
//
//        System.out.println("Assigned Hunter: " + assignedHunter.getName());
//        processMission(assignedHunter, criminal, priority);
//    }

    public void addMission(Criminal criminal, int priority) {
        scheduler.addMission(criminal, priority);
    }

    public void processMission(BountyHunter assignedHunter, Criminal criminal, int priority) {
        if (assignedHunter == null) {
            System.out.println("No suitable hunter found for this mission.");
            return;
        }


        // === Builder Pattern: Construct the mission ===
        MissionBuilder builder = new ConcreteMissionBuilder();
        builder.getMission().setCriminal(criminal);
        builder.getMission().setThreatLevel(priority);
        MissionDirector director = new MissionDirector(builder);
        director.constructMission();
        MissionProfile profile = builder.getMission();

        // === Decorator Pattern: Equip hunter with gadgets ===
        BountyHunter decoratedHunter = new GadgetDecorator(assignedHunter);

        // === Singleton: Ensure hunter is registered ===
        GuildRegistry.getInstance().registerHunter(assignedHunter.getName(), decoratedHunter);

        // === Command Pattern: Define mission actions ===
        Command track = new TrackCommand(assignedHunter, criminal);
        Command capture = new CaptureCommand(assignedHunter, criminal);
        MissionInvoker invoker = new MissionInvoker();
        invoker.addCommand(track);
        invoker.addCommand(capture);

        // === Mediator Pattern: Hunter ↔ Drone Coordination ===
        MissionControl mediator = new MissionControl();
        Colleague hunterColleague = new BountyHunterColleague(assignedHunter, mediator);
        Colleague droneColleague = new Drone("Recon-X", mediator);
        mediator.registerColleague(hunterColleague);
        mediator.registerColleague(droneColleague);
        mediator.coordinateMission(profile);

        // === Template Method Pattern: Execute mission ===
        MissionExecutionTemplate executor = new StandardMissionExecution(invoker);
        executor.executeMission(profile);
        assignedHunter.setAvailability(new AvailableState());
                System.out.println("[AVAILABILITY] " + assignedHunter.getName() +
                        " status changed: UNAVAILABLE → AVAILABLE\n   Reason: Mission cooldown complete");

//        // === Schedule cooldown timer to restore availability ===
//        Timer cooldownTimer = new Timer();
//        cooldownTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                assignedHunter.setAvailability(new AvailableState());
//                System.out.println("[AVAILABILITY] " + assignedHunter.getName() +
//                        " status changed: UNAVAILABLE → AVAILABLE\n   Reason: Mission cooldown complete");
//            }
//        }, 3000);  // 3 seconds for demo

    }

    public void setHunterUnavailable(String hunterName, String reason) {
        for (BountyHunter hunter : scheduler.getRegisteredHunters()) {
            if (hunter.getName().equals(hunterName)) {
                hunter.setAvailability(new UnavailableState(reason));
                break;
            }
        }
    }

    public void setHunterAvailable(String hunterName) {
        for (BountyHunter hunter : scheduler.getRegisteredHunters()) {
            if (hunter.getName().equals(hunterName)) {
                hunter.setAvailability(new AvailableState());
                break;
            }
        }
    }

    public void waitForMissions(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}