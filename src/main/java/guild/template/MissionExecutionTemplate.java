package guild.template;

import guild.builder.MissionProfile;

public abstract class MissionExecutionTemplate {
    protected MissionProfile mission;

    public final void executeMission(MissionProfile profile) {
        this.mission = profile;
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