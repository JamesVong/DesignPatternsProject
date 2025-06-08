package main.java.guild.builder;

public class ConcreteMissionBuilder implements MissionBuilder {
    private MissionProfile mission = new MissionProfile();

    @Override
    public void buildTarget() {
        mission.setTargetName("Darth Venom");
    }

    @Override
    public void buildLocation() {
        mission.setLocation("Outer Rim - Tatooine");
    }

    @Override
    public void buildEnvironment() {
        mission.setEnvironment("Desert terrain with high temperatures");
    }

    @Override
    public void buildHunter() {
        mission.setAssignedHunter("Mando");
    }

    @Override
    public void buildEquipment() {
        mission.setEquipment("Jetpack, Plasma Rifle, Thermal Scanner");
    }

    @Override
    public MissionProfile getMission() {
        return mission;
    }
}
