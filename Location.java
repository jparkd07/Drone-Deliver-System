     
/**
 * This class represents the place that restaurants, delivery services, and drones occupy.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Location {
    private final int xCoord;
    private final int yCoord;
    private int maxDrones;
    private String name;
    private int currentDrones;
    /**
     * Constructor for location.
     * @param name - Unique name
     * @param xCoord - int for x coord
     * @param yCoord - int for y coord
     * @param maxDrones - int for maxDrones
     */
    public Location(String name, int xCoord, int yCoord, int maxDrones) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.maxDrones = maxDrones;
        this.currentDrones = 0;
    }

    /**
     * Calculates the distance between two locations
     * @param destination Represents the other location
     * @return Returns a value representing the distance between the two locations
     */
    public Integer calcDistance(Location destination) {
         if (this.equals(destination)) {
           return 0;
         } else {
           return 1 + (int) Math.floor(Math.sqrt(Math.pow(getXCoord() - destination.getXCoord(), 2) + Math.pow(getYCoord() - destination.getYCoord(), 2)));
         }
    }

    /**
     * Getter for the name variable.
     * @return Returns the name variable
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("name: %s, (x,y): (%d, %d), space: [%d / %d] remaining",
                this.name, this.xCoord, this.yCoord, this.maxDrones - this.currentDrones, this.maxDrones);
    }

    /**
     * Updates drone spaces at the location.
     * @param howMany Represents how many spaces should be added to the location
     */
    public void updateSpacesLeft(int howMany) {
        this.currentDrones = this.currentDrones + howMany;
    }

    /**
     * Getter for the xCoord variable.
     * @return Returns the xCoord variable
     */
    public int getXCoord() {
        return this.xCoord;
    }

    /**
     * Getter for the yCoord variable.
     * @return Returns the yCoord variable
     */
    public int getYCoord() {
        return this.yCoord;
    }

    /**
     * Gets how many spaces are available at a location
     * @return Returns the amount of spaces remaining at a location
     */
    public Integer getRemaining() {
        return maxDrones - currentDrones;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Location)) {
            return false;
        } else{
            Location loc = (Location) obj;
            return this.getXCoord() == loc.getXCoord() && this.getYCoord() == loc.getYCoord();
        }
    }

    /**
     * Checks if there is a space left at a location.
     * @return Returns a boolean value indicating if the location has any spaces left
     */
    public boolean checkSpace() {
        return this.getRemaining() >= 1;
    }

}
Georgia Institute of Technology
Georgia Institute of Technology
Georgia Institute of Technology
© 2022 GitHub, Inc.
Help
Support
API
Training
Blog
About
GitHub Enterprise Server 3.4.2
Loading complete
