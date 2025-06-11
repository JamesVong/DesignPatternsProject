package guild.bridge;

public class Saber extends Weapon {
    public Saber(WeaponEffect effect) {
        super(effect);
    }

    @Override
    public void use() {
        System.out.println("[WEAPON] Saber: " + effect.applyEffect());
    }
}