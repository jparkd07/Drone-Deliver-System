/**
 * This class represents a payload consisting of ingredients carried by drones.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Payload {
    private Ingredient currentIngredient;
    private int quantity;
    private int unitPrice;

    /**
     * Instantiates a Payload object.
     * @param currentIngredient Represents the ingredient in the payload
     * @param quantity Represents the amount of the ingredient present in the payload
     * @param unitPrice Represents the unit price of the payload
     */
    public Payload(Ingredient currentIngredient, int quantity, int unitPrice) {
        this.currentIngredient = currentIngredient;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Adds an ingredient to the payload.
     * @param quantity Represents of the amount of the ingredient to be added to the payload
     */
    public void addIngredient(int quantity) {
        this.quantity += quantity;
    }

    /**
     * Removes an ingredient from the payload.
     * @param quantity Represents the amount of the ingredient to be removed from the payload
     */
    public void removeIngredient(int quantity) {
        this.quantity -= quantity;
    }

    /**
     * Gets the ingredient present in the payload by its barcode.
     * @return Returns the barcode of the ingredient present in the payload
     */
    public String getIngredientCode() {
        return this.currentIngredient.getID();
    }

    /**
     * Getter for the quantity variable.
     * @return Returns the quantity variable
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Calculates the weight of the entire payload with unitWeight and quantity variables
     * @return Returns the overall weight of the payload
     */
    public Integer getWeight() {
        return currentIngredient.getUnitWeight() * quantity;
    }

    /**
     * Getter for the unitPrice variable.
     * @return Returns the unitPrice variable.
     */
    public int getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return String.format("&> barcode: %s,  item_name: %s, total_quantity: %d, unit_cost: %d, total_weight: %d",
                currentIngredient.getID(), currentIngredient.getName(), quantity, unitPrice, getWeight());
    }
}
