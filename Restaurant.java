/**
 * This class represents a restaurant that purchases ingredients.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Restaurant {
    private Integer moneySpent;
    private String name;
    private int rating;
    private Location locatedAt;

    /**
     * Instantiates a Restaurant object.
     * Rating defaults to 0.
     * @param name Represents the name of the object
     * @param moneyBalance Represents the money balance of the restaurant
     * @param locatedAt Represents the location of the restaurant
     */
    public Restaurant (String name, Integer moneyBalance, Location locatedAt) {
        this.name = name;
        this.locatedAt = locatedAt;
        this.moneySpent = moneyBalance;
        //Rating doesn't matter yet, so it's going to stay at 0 for now
        rating = 0;
    }

    /**
     * Purchases an ingredient for the restaurant.
     * @param serviceProvider Represents the service provider to purchase the ingredient from
     * @param givenDrone Represents the drone used to deliver the ingredient
     * @param takenIngredient Represents the ingredient to be bought
     * @param quantity Represents the quantity of the ingredient to be bought
     */
    public void purchaseIngredient(DeliveryService serviceProvider, Drone givenDrone,
                                   Ingredient takenIngredient, Integer quantity) {
        Integer price = quantity * givenDrone.getPayload((takenIngredient.getID())).getUnitPrice();
        moneySpent += price;
        givenDrone.calculateEarnings(price);
        givenDrone.sellItems(takenIngredient, quantity);
        System.out.println("OK:change_completed");
    }

    /**
     * Getter for the locatedAt variable.
     * @return Returns the locatedAt variable
     */
    public Location getLoc() {
        return locatedAt;
    }

    @Override
    public String toString() {
        return String.format("name: %s, money_spent: $%d, location: %s", name, moneySpent, locatedAt.getName());
    }
}
