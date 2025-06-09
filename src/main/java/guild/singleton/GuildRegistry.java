package guild.singleton;

import guild.availability.AvailabilityState;
import guild.bounty.BountyHunter;
import guild.observer.AvailabilityObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuildRegistry {
    private static GuildRegistry instance;
    private Map<String, BountyHunter> registeredHunters = new HashMap<>();
    private final List<AvailabilityObserver> observers = new ArrayList<>();


    private GuildRegistry() {
        registeredHunters = new HashMap<>();
    }

    public static GuildRegistry getInstance() {
        if (instance == null) {
            instance = new GuildRegistry();
        }
        return instance;
    }

    public void registerHunter(String name, BountyHunter hunter) {
        registeredHunters.put(name, hunter);
        System.out.println("Hunter registered: " + name);
    }

    public BountyHunter getHunter(String name) {
        return registeredHunters.get(name);
    }

    public Map<String, BountyHunter> getAllHunters() {
        return registeredHunters;
    }

    public void notifyObservers(BountyHunter hunter, AvailabilityState oldState, AvailabilityState newState) {
        for (AvailabilityObserver observer : observers) {
            observer.onAvailabilityChange(hunter, oldState, newState);
        }
    }

    public void printAllHunters() {
        System.out.println("=== Registered Bounty Hunters ===");
        for (Map.Entry<String, BountyHunter> entry : registeredHunters.entrySet()) {
            System.out.println("Name " + entry.getKey() + ", Bounty Hunter: " + entry.getValue());
        }
    }
}
