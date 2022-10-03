import java.util.TreeMap;

/**
 * This class represents a payload consisting of ingredients carried by drones.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Drone {
    private Location currentLoc;
    private TreeMap<String, Payload> currentPayload = new TreeMap<>();
    private Integer droneTag;
    private Integer currentFuel;
    private Integer moneyEarned;
    private Integer capacity;
    private int currCapacityLeft;

    /**
     * Instantiates a Drone object.
     * @param currentLoc Represents the current location of the drone
     * @param capacity Represents the capacity of the drone
     * @param droneTag Represents the drone tag identifier of the drone
     * @param currentFuel Represents the current amount of fuel present in the drone
     */
    public Drone(Location currentLoc, Integer capacity, Integer droneTag, Integer currentFuel/*, DeliveryService ownedBy*/) {
        this.currentLoc = currentLoc;
        this.droneTag = droneTag;
        //this.needRepair = false;
        this.currentFuel = currentFuel;
        this.moneyEarned = 0;
        this.capacity = capacity;
        currCapacityLeft = capacity;
        currentLoc.updateSpacesLeft(1);
    }

    /**
     * Gets a payload carried by the drone.
     * @param barcode Represents the barcode of the ingredient in a payload to be retrieved
     * @return Returns the payload desired
     */
    public Payload getPayload(String barcode) {
        return currentPayload.get(barcode);
    }

    /**
     * Getter for the currentPayload variable.
     * @return Returns the currentPayload variable
     */
    public TreeMap<String, Payload> getPayload() {return currentPayload;}

    /**
     * Calculates the drone's overall earnings.
     * @param earnings Represents the amount of money to be added
     */
    public void calculateEarnings(Integer earnings) {
        moneyEarned += earnings;
    }

    /**
     * Sells items from the drone.
     * @param sold Represents the ingredient that is sold
     * @param quantity Represents the amount of the ingredient being sold
     */
    public void sellItems(Ingredient sold, Integer quantity) {
        currentPayload.get(sold.getID()).removeIngredient(quantity);
        currCapacityLeft += quantity;
        if(currentPayload.get(sold.getID()).getQuantity() == 0) {
            currentPayload.remove(sold.getID());
        }
    }

    /**
     * Loads a payload into the drone.
     * @param newPayload Represents the payload to be loaded onto the drone
     */
    public void loadPayload(Payload newPayload) {
        currentPayload.put((newPayload.getIngredientCode()), newPayload);
        currCapacityLeft -= newPayload.getQuantity();
    }

    /**
     * Loads fuel into the drone.
     * @param quantity Represents the amount of the fuel to be loaded onto the drone
     */
    public void loadFuel(int quantity) {
        this.currentFuel += quantity;
        System.out.println("OK:change_completed");
    }

    /**
     * Uses fuel in order for the drone to travel.
     * @param quantity Represents the amount of fuel used
     */
    public void useFuel(int quantity) {
        currentFuel -= quantity;
    }

    /**
     * Setter for the location variable.
     * @param newLoc Represents the new location that the drone occupies
     */
    public void setLocation(Location newLoc) {
        currentLoc = newLoc;
    }

    //commented out because at 11:51 decided to be moved to delivery service for visibility reasons.

    /**public void fly(Location destination) {
        currentLoc.updateSpacesLeft(-1);
        destination.updateSpacesLeft(1);
        this.currentFuel = this.currentFuel - currentLoc.calcDistance(destination);
        this.currentLoc = destination;
        if(destination.equals(ownedBy.getLoc())) {
            ownedBy.makeMoney(moneyEarned);
        }
    }**/

    @Override
    public String toString() {
        String payloadString = "";
        if (!currentPayload.isEmpty()) {

            for (Payload p : currentPayload.values()) {
                payloadString += p.toString();
            }
        }
        payloadString += "\n";
        return String.format("tag: %d, capacity: %d, remaining_cap: %d, fuel: %d, sales: $%d, location: %s\n%s",
                droneTag, capacity, currCapacityLeft, currentFuel, moneyEarned, currentLoc.getName(), payloadString);


    }

    /**public void setInSwarm(boolean inSwarm) {
        this.inSwarm = inSwarm;
    }**/



    /**
    private Integer calculateCurrentWeight() {
        Integer weight = 0;
        Set<String> set = currentPayload.keySet();
        for (String key: set) {
            weight += currentPayload.get(key).getWeight();
        }
        return weight;
    }
     **/

    /**
     * Getter for the currentLoc variable.
     * @return Returns the currentLoc variable
     */
    public Location getCurrentLoc() {
        return currentLoc;
    }

    /**
     * Getter for the droneTag variable.
     * @return Returns the droneTag variable
     */
    public Integer getDroneTag() {
        return droneTag;
    }

    /**
     * Getter for the fuel variable.
     * @return Returns the fuel variable
     */
    public int getFuel() {
        return this.currentFuel;
    }

    /**
     * Getter for the currCapacityLeft variable.
     * @return Returns the currCapacityLeft variable
     */
    public int getCurrCapacityLeft() {
        return this.currCapacityLeft;
    }

    /**
     * Getter for the moneyEarned variable
     * @return
     */
    public int getMoneyEarned() {
        return this.moneyEarned;
    }

    /**
     * Resets the moneyEarned variable to 0.
     */
    public void resetMoney(){
        this.moneyEarned = 0;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || !(obj instanceof Drone)) {
            return false;
        } else{
            Drone d = (Drone) obj;
            return this.droneTag == d.droneTag;
        }
    }
}
