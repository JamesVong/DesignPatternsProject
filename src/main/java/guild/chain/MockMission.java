package guild.chain;

public class MockMission {
    private String missionId;
    private int threatLevel; // 1 = low, 2 = mid, 3 = high

    public MockMission(String missionId, int threatLevel) {
        this.missionId = missionId;
        this.threatLevel = threatLevel;
    }

    public int getThreatLevel() {
        return threatLevel;
    }

    public String getMissionId() {
        return missionId;
    }
}
