package guild.builder;

import guild.criminal.Criminal;

public class MissionProfile {
    private String missionId;
    private String location;
    private String targetName;
    private String strategy;
    private Criminal criminal;
    private int riskLevel;

    public MissionProfile(String missionId, String location, String targetName,
                          String strategy, int riskLevel, Criminal criminal) {
        this.missionId = missionId;
        this.location = location;
        this.targetName = targetName;
        this.strategy = strategy;
        this.riskLevel = riskLevel;
        this.criminal = criminal;
    }

    // Getters
    public String getMissionId() { return missionId; }
    public String getLocation() { return location; }
    public String getTargetName() { return targetName; }
    public String getStrategy() { return strategy; }
    public int getRiskLevel() { return riskLevel; }
    public Criminal getCriminal() { return criminal; }

    @Override
    public String toString() {
        return "MissionProfile{" +
                "missionId='" + missionId + '\'' +
                ", location='" + location + '\'' +
                ", targetName='" + targetName + '\'' +
                ", strategy='" + strategy + '\'' +
                ", riskLevel=" + riskLevel +
                ", criminal=" + (criminal != null ? criminal.getName() : "null") +
                '}';
    }
}
