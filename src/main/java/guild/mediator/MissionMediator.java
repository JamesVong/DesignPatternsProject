package guild.mediator;

import guild.builder.MissionProfile;

public interface MissionMediator {
    void notify(String event, Colleague sender);
    void registerColleague(Colleague colleague);
    void coordinateMission(MissionProfile profile);
}