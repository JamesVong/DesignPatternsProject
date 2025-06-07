//package guild;

import guild.bounty.BountyHunter;
import guild.bounty.BountyHunterFactory;
import guild.bounty.MandalorianFactory;
import guild.bounty.ImperialFactory;
import guild.criminal.Criminal;
import guild.context.CaptureContext;
import guild.mission.MissionManager;

public class Main {

    public static void main(String[] args) {

        // Create different threat level criminals
        Criminal lowThreat = new Criminal("Greedo", "The Green Gunslinger", 2, "Mos Eisley Cantina");
        Criminal mediumThreat = new Criminal("Jango Fett", "The Original", 5, "Kamino");
        Criminal highThreat = new Criminal("Jabba Desilijic Tiure", "Jabba the Hutt", 9, "Tatooine Palace");

        // Create factories and hunters
        BountyHunterFactory mandalorianFactory = new MandalorianFactory();
        BountyHunterFactory imperialFactory = new ImperialFactory();

        BountyHunter dinDjarin = mandalorianFactory.recruitHunter("Din Djarin", "Silver");
        BountyHunter imperialAgent = imperialFactory.recruitHunter("Agent Kallus", "Commander");
        BountyHunter bobaFett = mandalorianFactory.recruitHunter("Boba Fett", "Gold");

        // Strategy + Decorator Pattern Demo

        System.out.println("=".repeat(80));

        CaptureContext context = new CaptureContext();

        System.out.println("MISSION 1: Low Threat Capture");
        context.executeMission(dinDjarin, lowThreat);

        System.out.println("\n" + "=".repeat(80));
        System.out.println("MISSION 2: Medium Threat Capture");
        context.executeMission(imperialAgent, mediumThreat);

        // State Pattern

        System.out.println("=".repeat(80));

        MissionManager missionManager = new MissionManager();

        // Execute mission with state pattern
        missionManager.executeMissionWithStates(bobaFett, highThreat);

        // Demonstrate state transition control

        System.out.println("=".repeat(80));
        demonstrateStateControl();

    }

    private static void demonstrateStateControl() {

        BountyHunterFactory factory = new MandalorianFactory();
        BountyHunter testHunter = factory.recruitHunter("Test Hunter", "Bronze");
        Criminal testCriminal = new Criminal("Test Target", "The Tester", 3, "Test Location");

        // Create context but don't complete actions
        guild.state.HunterContext hunterContext = new guild.state.HunterContext(testHunter);

        // Complete current action and advance
        hunterContext.performCurrentAction(testCriminal);
        hunterContext.proceedToNextState();

    }
}