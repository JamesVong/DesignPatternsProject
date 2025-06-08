package guild.chain;

public abstract class HunterAssignmentHandler {
    protected HunterAssignmentHandler next;

    public void setNext(HunterAssignmentHandler next) {
        this.next = next;
    }

    public abstract void assignHunter(MockMission mission);
}
