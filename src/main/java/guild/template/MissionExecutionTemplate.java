package main.java.guild.template;

public abstract class MissionExecutionTemplate {

    public final void executeMission() {
        gatherIntel();
        approachTarget();
        engageTarget();
        extract();
        reportBack();
    }

    protected abstract void gatherIntel();
    protected abstract void approachTarget();
    protected abstract void engageTarget();
    protected abstract void extract();

    protected void reportBack() {
        System.out.println("Reporting back to base. Mission complete.");
    }
}
