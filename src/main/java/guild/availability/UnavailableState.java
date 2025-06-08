package guild.availability;

public class UnavailableState implements AvailabilityState {
    private String reason;

    public UnavailableState() {
        this.reason = "On active mission";
    }

    public UnavailableState(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public String getStatus() {
        return "UNAVAILABLE";
    }

    @Override
    public String getDescription() {
        return reason;
    }

    public String getReason() {
        return reason;
    }
}