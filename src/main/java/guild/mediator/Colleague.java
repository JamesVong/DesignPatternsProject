package guild.mediator;

import guild.builder.MissionProfile;

public abstract class Colleague {
    protected MissionMediator mediator;

    public Colleague(MissionMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receiveMission(MissionProfile profile);

}