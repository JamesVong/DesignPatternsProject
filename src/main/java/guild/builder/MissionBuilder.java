package guild.builder;

public interface MissionBuilder {
    void buildTarget();
    void buildLocation();
    void buildEnvironment();
    void buildHunter();
    void buildEquipment();
    MissionProfile getMission();
}

