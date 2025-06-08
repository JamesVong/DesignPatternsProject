package main.java.guild.template;

public class StandardMissionExecution extends MissionExecutionTemplate {

    @Override
    protected void gatherIntel() {
        System.out.println("Gathering satellite intel and enemy movement data...");
    }

    @Override
    protected void approachTarget() {
        System.out.println("Approaching target using ground stealth route...");
    }

    @Override
    protected void engageTarget() {
        System.out.println("Engaging target using non-lethal methods...");
    }

    @Override
    protected void extract() {
        System.out.println("Extracting target using grappling drone...");
    }
}
