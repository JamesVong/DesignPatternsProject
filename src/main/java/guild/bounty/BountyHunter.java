package guild.bounty;

import guild.criminal.Criminal;
import guild.availability.AvailabilityState;
import guild.availability.AvailableState;
import guild.observer.AvailabilityObserver;
import guild.singleton.GuildRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class BountyHunter {
    protected String name;
    protected String faction;
    protected String rank;
    protected AvailabilityState availabilityState;
    protected List<AvailabilityObserver> observers;

    public BountyHunter(String name, String faction, String rank) {
        this.name = name;
        this.faction = faction;
        this.rank = rank;
        this.availabilityState = new AvailableState(); // Default to available
        this.observers = new ArrayList<>();
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

    public void setRank(String rank) {
        this.rank = rank;
    }

    public boolean isAvailable() {
        return availabilityState.isAvailable();
    }

    public String getAvailabilityStatus() {
        return availabilityState.getStatus();
    }

    public AvailabilityState getAvailabilityState() {
        return availabilityState;
    }

    public void setAvailability(AvailabilityState newState) {
        if (this.availabilityState.getStatus().equals(newState.getStatus())) {
            // Avoid re-notifying if the status is the same
            return;
        }

        AvailabilityState oldState = this.availabilityState;
        this.availabilityState = newState;

        System.out.println("[AVAILABILITY] " + name + " status changed: " +
                oldState.getStatus() + " → " + newState.getStatus());
        System.out.println("   Reason: " + newState.getDescription());

        notifyObservers(oldState, newState);
    }




    // Observer management methods
    public void addObserver(AvailabilityObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[OBSERVER] Observer registered for " + name);
        }
    }

    public void removeObserver(AvailabilityObserver observer) {
        observers.remove(observer);
        System.out.println("[OBSERVER] Observer unregistered for " + name);
    }

    private void notifyObservers(AvailabilityState oldState, AvailabilityState newState) {
        System.out.println("[NOTIFICATION] Notifying " + observers.size() + " observers about " + name
                + "'s availability change");
        for (AvailabilityObserver observer : observers) {
            observer.onAvailabilityChange(this, oldState, newState);
        }
    }

    public abstract void track(Criminal target);

    public abstract void capture(Criminal target);

    public abstract void reportStatus();
}