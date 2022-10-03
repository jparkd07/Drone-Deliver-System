/**
 * This class represents an ingredient used by restaurants.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Ingredient {
    private final String barcode;
    private Integer unitWeight;
    private String name;

    /**
     * Instantiates an Ingredient object.
     * @param barcode Represents the identifier for the ingredient
     * @param unitWeight Represents the unit weight of an ingredient
     * @param name Represents the name of the ingredient
     */
    public Ingredient(String barcode, Integer unitWeight, String name) {
        this.barcode = barcode;
        this.unitWeight = unitWeight;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("barcode: %s, name: %s, unit_weight: %d", barcode, name, unitWeight);
    }

    /**
     * Getter for the unitWeight variable.
     * @return Returns the unit weight of the ingredient
     */
    public Integer getUnitWeight() {
        return unitWeight;
    }

    /**
     * Getter for the name variable.
     * @return Returns the name of the ingredient
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for the barcode variable.
     * @return Returns the barcode of the ingredient
     */
    public String getID(){
        return this.barcode;
    }
}
