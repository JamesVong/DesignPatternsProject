package guild.command;

public class CommandTest {
    public static void main(String[] args) {
        MockBountyHunter hunter = new MockBountyHunter("Mando");

        Command track = new TrackCommand(hunter);
        Command capture = new CaptureCommand(hunter);
        Command abort = new AbortMissionCommand(hunter);

        MissionInvoker invoker = new MissionInvoker();
        invoker.addCommand(track);
        invoker.addCommand(capture);
        invoker.addCommand(abort);

        invoker.executeAll();
    }
}
