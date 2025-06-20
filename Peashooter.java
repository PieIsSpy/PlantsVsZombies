public class Peashooter extends Plant {

    public Peashooter(float r, float c)
    {
        super(r, c);
        setName("Peashooter");
        setCost(100);
        setCooldown(7);
        setRange(7);
        setDamage(20);
        setHealth(30);
        setDirectDamage(30);

    }

    public String PlantBehavior()
    {
        return getName() + " shot a pea at zombie";
    }
}
