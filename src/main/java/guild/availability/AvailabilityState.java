package main.java.guild.availability;

public interface AvailabilityState {
    boolean isAvailable();

    String getStatus();

    String getDescription();
}