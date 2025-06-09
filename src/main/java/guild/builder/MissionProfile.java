package guild.builder;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

public class MissionProfile {
    private String targetName;
    private String location;
    private String environment;
    private String assignedHunter;
    private String equipment;
    private Criminal criminal;
    private int threatLevel;
    private BountyHunter hunter;

    public void setAssignedHunter(BountyHunter hunter) {
        this.hunter = hunter;
    }

    public BountyHunter getHunter() {
        return this.hunter;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getAssignedHunter() {
        return assignedHunter;
    }

    public void setAssignedHunter(String assignedHunter) {
        this.assignedHunter = assignedHunter;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Criminal getCriminal() {
        return criminal;
    }

    public void setCriminal(Criminal criminal) {
        this.criminal = criminal;
    }

    public int getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(int threatLevel) {
        this.threatLevel = threatLevel;
    }

    public void displayProfile() {
        System.out.println("=== Mission Profile ===");
        System.out.println("Target: " + targetName);
        System.out.println("Location: " + location);
        System.out.println("Environment: " + environment);
        System.out.println("Hunter: " + assignedHunter);
        System.out.println("Equipment: " + equipment);
    }
}
