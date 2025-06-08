package guild.chain;
import guild.chain.*;

public class ChainTest {
    public static void main(String[] args) {
        HunterAssignmentHandler low = new LowTierHunterHandler();
        HunterAssignmentHandler mid = new MidTierHunterHandler();
        HunterAssignmentHandler high = new HighTierHunterHandler();

        low.setNext(mid);
        mid.setNext(high);

        MockMission m1 = new MockMission("M-101", 1);
        MockMission m2 = new MockMission("M-102", 2);
        MockMission m3 = new MockMission("M-103", 3);
        MockMission m4 = new MockMission("M-404", 4); // No one handles

        low.assignHunter(m1);
        low.assignHunter(m2);
        low.assignHunter(m3);
        low.assignHunter(m4);
    }
}
