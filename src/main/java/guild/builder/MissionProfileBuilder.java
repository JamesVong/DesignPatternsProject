package guild.builder;

import guild.criminal.Criminal;

public class MissionProfileBuilder {
    private String missionId;
    private String location;
    private String targetName;
    private String strategy;
    private int riskLevel;
    private Criminal criminal;

    public MissionProfileBuilder setMissionId(String missionId) {
        this.missionId = missionId;
        return this;
    }

    public MissionProfileBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public MissionProfileBuilder setTargetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public MissionProfileBuilder setStrategy(String strategy) {
        this.strategy = strategy;
        return this;
    }

    public MissionProfileBuilder setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }

    public MissionProfileBuilder setCriminal(Criminal criminal) {
        this.criminal = criminal;
        return this;
    }

    public MissionProfile build() {
        return new MissionProfile(missionId, location, targetName, strategy, riskLevel, criminal);
    }
}
