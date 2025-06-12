package guild.facade;

import guild.adapter.CriminalAdapter;
import guild.availability.AvailableState;
import guild.availability.UnavailableState;
import guild.bounty.BountyHunter;
import guild.bounty.BountyHunterFactory;
import guild.bounty.ImperialFactory;
import guild.bounty.MandalorianFactory;
import guild.bridge.*;
import guild.builder.MissionProfile;
import guild.builder.MissionProfileBuilder;
import guild.chain.HunterAssignmentHandler;
import guild.chain.LowTierHunterHandler;
import guild.chain.MidTierHunterHandler;
import guild.chain.HighTierHunterHandler;
import guild.command.CaptureCommand;
import guild.command.Command;
import guild.command.MissionInvoker;
import guild.command.TrackCommand;
import guild.criminal.Criminal;
import guild.database.Database;
import guild.database.DatabaseProxy;
import guild.decorator.GadgetDecorator;
import guild.mediator.BountyHunterColleague;
import guild.mediator.Colleague;
import guild.mediator.Drone;
import guild.mediator.MissionControl;
import guild.scheduler.MissionScheduler;
import guild.singleton.GuildRegistry;
import guild.template.MissionExecutionTemplate;
import guild.template.StandardMissionExecution;

import java.util.List;

public class MissionFacade {

    private final Database criminalDatabase;
    private final CriminalAdapter criminalAdapter;
    private final MissionScheduler scheduler;
    private final BountyHunterFactory mandalorianFactory;
    private final BountyHunterFactory imperialFactory;
    private int missionCounter = 1;

    public MissionFacade(String criminalFilePath) {
        this.criminalDatabase = new DatabaseProxy(criminalFilePath);
        this.criminalAdapter = new CriminalAdapter(criminalDatabase);
        this.scheduler = MissionScheduler.getInstance();
        this.mandalorianFactory = new MandalorianFactory();
        this.imperialFactory = new ImperialFactory();
    }

    public List<Criminal> fetchCriminals() {
        return criminalAdapter.getCriminals();
    }

    public void recruitHunters() {
        WeaponEffect plasma = new PlasmaBolt();
        WeaponEffect blade = new VibroBlade();

        Weapon blaster = new Blaster(plasma);
        Weapon saber = new Saber(blade);

        BountyHunter dinDjarin = mandalorianFactory.recruitHunter("Din Djarin", "Silver");
        dinDjarin.setWeapon(blaster);
        BountyHunter bobaFett = mandalorianFactory.recruitHunter("Boba Fett", "Gold");
        bobaFett.setWeapon(blaster);
        BountyHunter imperialAgent = imperialFactory.recruitHunter("Agent Kallus", "Commander");
        imperialAgent.setWeapon(saber);

        // Register hunters with singleton registry (which also attaches observer)
        GuildRegistry.getInstance().registerHunter("Din Djarin", dinDjarin);
        GuildRegistry.getInstance().registerHunter("Boba Fett", bobaFett);
        GuildRegistry.getInstance().registerHunter("Agent Kallus", imperialAgent);
    }

    public void displaySchedulerStatus() {
        scheduler.displayStatus();
    }

    public void addMission(Criminal criminal, int priority) {
        scheduler.addMission(criminal, priority);
    }

    public void processMission(String missionId, BountyHunter assignedHunter, Criminal criminal, int priority) {
        if (assignedHunter == null) {
            System.out.println("No suitable hunter found for this mission.");
            return;
        }

        // === Builder Pattern: Construct the mission ===
        MissionProfile profile = new MissionProfileBuilder()
                .setMissionId(missionId)
                .setLocation(criminal.getLastKnownLocation())
                .setTargetName(criminal.getName())
                .setStrategy("stealth")
                .setRiskLevel(priority)
                .setCriminal(criminal)
                .build();

        // === Decorator Pattern: Equip hunter with gadgets (optional visual/logical enhancement) ===
        BountyHunter decoratedHunter = new GadgetDecorator(assignedHunter);
        // Note: decoratedHunter not used further — optionally replace assignedHunter everywhere below

        // === Command Pattern: Define mission actions ===
        Command track = new TrackCommand(assignedHunter, criminal);
        Command capture = new CaptureCommand(assignedHunter, criminal);
        MissionInvoker invoker = new MissionInvoker();
        invoker.addCommand(track);
        invoker.addCommand(capture);

        assignedHunter.setAvailability(new UnavailableState("On active mission"));

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

        // Restore hunter availability after mission
        assignedHunter.setAvailability(new AvailableState());
    }

    public void setHunterUnavailable(String hunterName, String reason) {
        for (BountyHunter hunter : GuildRegistry.getInstance().getAllHunters().values()) {
            if (hunter.getName().equals(hunterName)) {
                hunter.setAvailability(new UnavailableState(reason));
                break;
            }
        }
    }

    public void setHunterAvailable(String hunterName) {
        for (BountyHunter hunter : GuildRegistry.getInstance().getAllHunters().values()) {
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
