/**
 * This class handles the cooldowns per 
 * plant type. It decides when the type of 
 * plant is ready to be planted in the game
 * again. 
 * 
 */

public class Cooldown {

    /**
     * This constructor initializes the values for the 
     * type of plant and its respective cooldown. It also
     * sets the time of when the plant was last planted to a
     * default value of -888. 
     * 
     * @param type plant type
     * @param cd cooldowns of each plant type
     */
    public Cooldown(String type, int cd)
    {
        cooldown = cd;
        lastPlaced = -888;
        plantType = type;
    }

    /**
     * This method determines if the type of plant
     * has finished its cooldown and is ready to be 
     * planted again. 
     * 
     * @param currentTime the current time in the game 
     * @return true if the plant type has finished its cooldown, 
     * false otherwise.  
     */
    public boolean isReady(int currentTime)
    {
        return currentTime >= lastPlaced + cooldown;
    }

    /**
     * This method triggers the cooldown of the 
     * plant type and is used when the plant was recently 
     * planted. It initializes the time the plant was last planted to
     * the current time of the game. 
     * 
     * @param currentTime current time in the game
     */
    public void updateLastPlaced(int currentTime)
    {
        lastPlaced = currentTime;
    }

    /**
     * This method returns how much time is left
     * before the type of plant can be planted again. 
     * 
     * @param currentTime current time in the game
     * @return remaining time left before cooldown is over 
     */
    public int getRemainingTime(int currentTime)
    {
        int remaining = (lastPlaced + cooldown) - currentTime;
        return Math.max(0, remaining);
    }

    /**
     * This method returns the type of plant. 
     * 
     * @return plant type
     */
    public String getPlantType()
    {
        return plantType;
    }


    private int cooldown; //time it takes for a plant type to be planted again
    private int lastPlaced; //time of when the plant type was last planted
    private String plantType; //type of plant(e.g., Sunflower, Peashooter, etc)
}
