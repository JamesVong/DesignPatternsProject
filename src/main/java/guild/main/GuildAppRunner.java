import guild.facade.MissionFacade;
import guild.criminal.Criminal;
import guild.singleton.GuildRegistry;

import java.util.List;

public class GuildAppRunner {

    public static void run() {
        // Initialize facade, register hunters as you have
        String criminalFilePath = "src/main/java/resources/criminals.csv";
        MissionFacade missionFacade = new MissionFacade(criminalFilePath);
        List<Criminal> criminals = missionFacade.fetchCriminals();

        // Optionally print criminals with info showing Adapter + Proxy + Composite + Visitor effects
        System.out.println("Criminals loaded with Adapter, Proxy, Composite, Visitor patterns:");

        criminals.forEach(System.out::println);

        missionFacade.recruitHunters(); // Hunters created via Factory, registered with observer

        missionFacade.displaySchedulerStatus();

        // Submit missions, showcasing Chain, Builder, Command, Template, Mediator, Decorator
        missionFacade.addMission(criminals.get(2), 8); // High priority
        missionFacade.addMission(criminals.get(1), 5); // Medium
        missionFacade.addMission(criminals.get(0), 3); // Low

        missionFacade.waitForMissions(3000);
        missionFacade.displaySchedulerStatus();

        missionFacade.waitForMissions(5000);

        // Simulate hunters busy (state change triggers observer, scheduler queues missions)
        missionFacade.setHunterUnavailable("Din Djarin", "Maintenance");
        missionFacade.setHunterUnavailable("Boba Fett", "Medical");
        missionFacade.displaySchedulerStatus();

        // Submit more missions while hunters are busy (go to scheduler queue)
        missionFacade.addMission(criminals.get(3), 10); // Highest priority
        missionFacade.addMission(criminals.get(4), 6);

        missionFacade.displaySchedulerStatus();

        // Set hunters back available (triggers scheduler observer to assign queued missions)
        missionFacade.setHunterAvailable("Din Djarin");
        missionFacade.setHunterAvailable("Boba Fett");

        missionFacade.waitForMissions(2000);
        missionFacade.displaySchedulerStatus();

        // Print final hunter states and gadgets (Decorator pattern effects)
        GuildRegistry.getInstance().printAllHunters();
    }
}