package main.java.guild.availability;

public class AvailableState implements AvailabilityState {

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getStatus() {
        return "AVAILABLE";
    }

    @Override
    public String getDescription() {
        return "Ready for assignment";
    }
}