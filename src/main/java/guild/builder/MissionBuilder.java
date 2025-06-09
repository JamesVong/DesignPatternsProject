package guild.builder;

import guild.criminal.Criminal;

public interface MissionBuilder {
    void buildTarget();
    void buildLocation();
    void buildEnvironment();
    void buildHunter();
    void buildEquipment();
    MissionProfile getMission();
    void setCriminal(Criminal criminal);
    void setThreatLevel(int threatLevel);
}

