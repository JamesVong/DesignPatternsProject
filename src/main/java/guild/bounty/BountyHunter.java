package guild.bounty;

import guild.criminal.Criminal;

public abstract class BountyHunter {

    protected String name;
    protected String faction;
    protected String rank;

    public BountyHunter(String name, String faction, String rank) {
        this.name = name;
        this.faction = faction;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public String getFaction() {
        return faction;
    }

    public String getRank() {
        return rank;
    }

    public abstract void track(Criminal target);

    public abstract void capture(Criminal target);

    public abstract void reportStatus();
}
