public class Level1 extends Level{
    public Level1(int currentTime) {
        super(1,180,5,9,currentTime);

        Plant[] p = new Plant[]{new Sunflower(-1, -1, 0),
                new Peashooter(-1, -1, 0)};

        initializePlants(p);
    }
}
