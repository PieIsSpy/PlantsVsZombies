public class FlagZombie extends Zombie{
    public FlagZombie (int r, int c, int t) {
        super(r, c, t);
        setSpeed(3);
        setHeld_item(new Item("Flag"));
    }

}
