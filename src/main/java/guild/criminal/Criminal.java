package guild.criminal;

import guild.visitor.CriminalVisitor;

public class Criminal {

    protected String name;
    protected String alias;
    protected int threatLevel;
    protected String lastKnownLocation;

    public Criminal(String name, String alias, int threatLevel, String lastKnownLocation) {
        this.name = name;
        this.alias = alias;
        this.threatLevel = threatLevel;
        this.lastKnownLocation = lastKnownLocation;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public int getThreatLevel() {
        return threatLevel;
    }

    public String getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void accept(CriminalVisitor visitor) {
        visitor.visit(this);
    }

    public void displayProfile() {
        System.out.println("Name: " + name);
        System.out.println("Alias: " + alias);
        System.out.println("Threat Level: " + threatLevel);
        System.out.println("Last Known Location: " + lastKnownLocation);
    }
}
