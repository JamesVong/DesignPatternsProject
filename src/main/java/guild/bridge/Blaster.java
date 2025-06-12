package guild.bridge;

public class Blaster extends Weapon {
    public Blaster(WeaponEffect effect) {
        super(effect);
    }

    @Override
    public void use() {
        System.out.println("[WEAPON] Blaster: " + effect.applyEffect());
    }
}
