package main.java.guild.observer;

import main.java.guild.bounty.BountyHunter;
import main.java.guild.availability.AvailabilityState;

public interface AvailabilityObserver {
    void onAvailabilityChange(BountyHunter hunter, AvailabilityState oldState, AvailabilityState newState);
}