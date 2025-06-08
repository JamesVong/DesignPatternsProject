package main.java.guild.builder;

public class MissionProfile {
    private String targetName;
    private String location;
    private String environment;
    private String assignedHunter;
    private String equipment;

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setAssignedHunter(String assignedHunter) {
        this.assignedHunter = assignedHunter;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
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
