package main.java.guild.state;

import main.java.guild.criminal.Criminal;

public interface HunterState {
    void performAction(HunterContext context, Criminal criminal);

    void nextState(HunterContext context);

    String getStateName();
}