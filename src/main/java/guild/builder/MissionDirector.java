package main.java.guild.builder;

public class MissionDirector {
    private MissionBuilder builder;

    public MissionDirector(MissionBuilder builder) {
        this.builder = builder;
    }

    public MissionProfile constructMission() {
        builder.buildTarget();
        builder.buildLocation();
        builder.buildEnvironment();
        builder.buildHunter();
        builder.buildEquipment();
        return builder.getMission();
    }
}
