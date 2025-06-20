public class Peashooter extends Plant {

    public Peashooter()
    {
        //range to be edited
        super("Peashooter", 100, 7, 300, 0, 20, 20, 1.5f); 

    }

    public String PlantBehavior()
    {
        return getName() + " shot a pea at zombie";
    }
}
