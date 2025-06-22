
public class Projectile {

    Projectile(float c, int dmg, float s)
    {
        col = c;
        speed = s;
        damage = dmg;
        isActive = true;
    }

    public void hitEnemy(Zombie z)
    {
        z.takeDamage(damage);
        deactivateProjectile();

        System.out.println("Projectile has hit zombie at ( " + z.getRow() + ", " + z.getCol() + ")");
    }

    public void moveProjectile()
    {
        float pos = getColumn();

        pos += (float)(1.0 / speed);
        setColumn(pos);
        
    }

    public void deactivateProjectile()
    {
        isActive = false;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setColumn(float c)
    {
        col = c;
    }

    public float getColumn()
    {
        return col;
    }

    private float col;
    private int damage;
    private float speed;
    /**flag to check if projectile has not hit zombie or not gone out of the game grid yet*/
    private boolean isActive;
}
