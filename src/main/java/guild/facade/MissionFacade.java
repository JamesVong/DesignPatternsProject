package guild.facade;

import guild.bounty.BountyHunter;
import guild.bounty.BountyHunterFactory;
import guild.bounty.MandalorianFactory;
import guild.bounty.ImperialFactory;
import guild.criminal.Criminal;
import guild.scheduler.MissionScheduler;
import guild.availability.AvailableState;
import guild.availability.UnavailableState;
import guild.adapter.CriminalAdapter;
import guild.database.Database;
import guild.database.DatabaseProxy;

import java.util.List;

public class MissionFacade {

    private final Database criminalDatabase;
    private final CriminalAdapter criminalAdapter;
    private final MissionScheduler scheduler;
    private final BountyHunterFactory mandalorianFactory;
    private final BountyHunterFactory imperialFactory;

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

    public void addMission(Criminal criminal, int priority) {
        scheduler.addMission(criminal, priority);
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