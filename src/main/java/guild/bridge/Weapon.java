package guild.bridge;

public abstract class Weapon {
    protected WeaponEffect effect;

    public Weapon(WeaponEffect effect) {
        this.effect = effect;
    }

    public abstract void use();
}
