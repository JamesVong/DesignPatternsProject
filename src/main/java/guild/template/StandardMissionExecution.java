package guild.template;

import guild.command.MissionInvoker;

public class StandardMissionExecution extends MissionExecutionTemplate {
    private MissionInvoker invoker;

    public StandardMissionExecution(MissionInvoker invoker) {
        this.invoker = invoker;
    }

    @Override
    protected void gatherIntel() {
        System.out.println("Gathering satellite intel and enemy movement data...");
    }

    @Override
    protected void approachTarget() {
        System.out.println("Approaching target using ground stealth route...");
    }

    @Override
    protected void engageTarget() {
        System.out.println("Executing mission commands...");
        if (invoker != null) {
            invoker.executeAll();
        }
    }

    @Override
    protected void extract() {
        System.out.println("Extracting target using grappling drone...");
    }
}
