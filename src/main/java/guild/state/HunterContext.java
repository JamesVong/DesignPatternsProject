package guild.state;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

public class HunterContext {
    private HunterState currentState;
    private BountyHunter hunter;
    private boolean actionCompleted;

    public HunterContext(BountyHunter hunter) {
        this.hunter = hunter;
        this.currentState = new EquippedState();
        this.actionCompleted = false;
        System.out.println("Hunter Context initialized - " + hunter.getName() + " is now EQUIPPED");
    }

    public void setState(HunterState state) {
        this.currentState = state;
        this.actionCompleted = false;
        System.out
                .println("State transition: " + hunter.getName() + " is now in " + state.getStateName() + " state");
    }

    public HunterState getCurrentState() {
        return currentState;
    }

    public BountyHunter getHunter() {
        return hunter;
    }

    public void setActionCompleted(boolean completed) {
        this.actionCompleted = completed;
    }

    public boolean isActionCompleted() {
        return actionCompleted;
    }

    public void performCurrentAction(Criminal criminal) {
        System.out.println("\n " + currentState.getStateName().toUpperCase() + " PHASE");
        currentState.performAction(this, criminal);
    }

    public void proceedToNextState() {
        if (actionCompleted) {
            currentState.nextState(this);
        } else {
            System.out.println(
                    "Cannot proceed - current action not completed in " + currentState.getStateName() + " state");
        }
    }

    public boolean canProceed() {
        return actionCompleted;
    }
}