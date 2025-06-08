package guild.observer;

import guild.bounty.BountyHunter;
import guild.availability.AvailabilityState;

public interface AvailabilityObserver {
    void onAvailabilityChange(BountyHunter hunter, AvailabilityState oldState, AvailabilityState newState);
}