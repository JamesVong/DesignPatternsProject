package main.java.guild.scheduler;

import main.java.guild.criminal.Criminal;

public class Mission {
    private String missionId;
    private Criminal target;
    private int priority;
    private long createdTime;

    public Mission(String missionId, Criminal target, int priority) {
        this.missionId = missionId;
        this.target = target;
        this.priority = priority;
        this.createdTime = System.currentTimeMillis();
    }

    public String getMissionId() {
        return missionId;
    }

    public Criminal getTarget() {
        return target;
    }

    public int getPriority() {
        return priority;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return "Mission[" + missionId + ": " + target.getAlias() + " (Priority: " + priority + ")]";
    }
}