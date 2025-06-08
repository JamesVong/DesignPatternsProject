package guild.criminal;

import java.util.ArrayList;
import java.util.List;
import guild.visitor.CriminalVisitor;

public class Gang extends Criminal {

    private final List<Criminal> members = new ArrayList<>();

    public Gang(String name, String alias, int threatLevel, String lastKnownLocation) {
        super(name, alias, threatLevel, lastKnownLocation);
    }

    public void addMember(Criminal criminal) {
        members.add(criminal);
    }

    public void removeMember(Criminal criminal) {
        members.remove(criminal);
    }

    public List<Criminal> getMembers() {
        return members;
    }

    @Override
    public void displayProfile() {
        System.out.println("=== Gang: " + name + " aka " + alias + " ===");
        System.out.println("Base Threat Level: " + threatLevel);
        System.out.println("Base Location: " + lastKnownLocation);
        System.out.println("Members:");

        for (Criminal c : members) {
            System.out.println(" - " + c.getName() + " (Threat: " + c.getThreatLevel() + ")");
        }
    }

    @Override
    public void accept(CriminalVisitor visitor) {
        visitor.visit(this);
    }

    // Optional: sum total threat
    public int getTotalThreatLevel() {
        int total = this.threatLevel;
        for (Criminal c : members) {
            total += c.getThreatLevel();
        }
        return total;
    }
}
