package guild.state;

import guild.criminal.Criminal;

public interface HunterState {
    void performAction(HunterContext context, Criminal criminal);

    void nextState(HunterContext context);

    String getStateName();
}