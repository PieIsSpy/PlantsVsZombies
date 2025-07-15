public class FlagZombie extends Zombie{
    public FlagZombie (int r, int c, int t) {
        super(r, c, t, new Item("Flag"));
        setSpeed(3);
    }

    @Override
    public void sprite_animation() {

    }
}
