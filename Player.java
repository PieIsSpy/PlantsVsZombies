public class Player {

    public Player (int s) {
        sun = s;
    }

    public int getSun() {
        return sun;
    }

    public void placePlant(Level l, int r, int c, String n, int t) {
        if (n.equalsIgnoreCase("sunflower"))
            l.getTiles()[r][c] = new Sunflower(r,c,t);
        else if (n.equalsIgnoreCase("peashooter"))
            l.getTiles()[r][c] = new Peashooter(r,c, t);
    }

    public void useShovel(Level l, int r, int c) {
        l.getTiles()[r][c] = null;
    }

    public void collectSun(int s) {
        sun += s;
    }

    public void subtractSun(int s) {
        sun -= s;
    }

    private int sun;
}