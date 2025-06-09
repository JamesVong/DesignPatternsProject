package guild.command;

public class MockBountyHunter {
    private final String name;

    public MockBountyHunter(String name) {
        this.name = name;
    }

    public void track() {
        System.out.println(name + " is tracking the target...");
    }

    public void capture() {
        System.out.println(name + " has captured the target!");
    }

    public void abort() {
        System.out.println(name + " has aborted the mission.");
    }
}
