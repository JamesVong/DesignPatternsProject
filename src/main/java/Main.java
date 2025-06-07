//package guild;

import guild.bounty.BountyHunter;
import guild.bounty.BountyHunterFactory;
import guild.bounty.MandalorianFactory;
import guild.bounty.ImperialFactory;
import guild.criminal.Criminal;
import guild.context.CaptureContext;

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

        // Create capture context
        CaptureContext context = new CaptureContext();

        // Execute missions with different threat levels
        System.out.println("MISSION 1: Low Threat Capture");
        context.executeMission(dinDjarin, lowThreat);

        System.out.println("\n" + "=".repeat(80));
        System.out.println("MISSION 2: Medium Threat Capture");
        context.executeMission(imperialAgent, mediumThreat);

        System.out.println("\n" + "=".repeat(80));
        System.out.println("MISSION 3: High Threat Capture");
        context.executeMission(bobaFett, highThreat);

    }
}