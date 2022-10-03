import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class represents a warehouse employee hired by a delivery service.
 * It is a child class of Person.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class WarehouseEmployee extends Person {
    private ArrayList<String> employedAt;

    /**
     * Instantiates a WarehouseEmployee object.
     * @param userName Represents the username of the person
     * @param firstName Represents the first name of the person
     * @param lastName Represents the last name of the person
     * @param birthday Represents the person's birthday
     * @param address Represents the person's address
     * @param pilotsID Represents the person's pilot ID
     * @param experience Represents the person's experience
     */
    public WarehouseEmployee(String userName, String firstName, String lastName,
                             GregorianCalendar birthday, String address, String pilotsID, int experience) {
        super(userName, firstName, lastName, birthday, address, pilotsID, experience);
        employedAt = new ArrayList<>();
    }

    /**
     * Instantiates a WarehouseEmployee object.
     * @param userName Represents the username of the person
     * @param firstName Represents the first name of the person
     * @param lastName Represents the last name of the person
     * @param birthday Represents the person's birthday
     * @param address Represents the person's address
     */
    public WarehouseEmployee(String userName, String firstName, String lastName,
    GregorianCalendar birthday, String address) {
        this(userName, firstName,lastName, birthday, address, null, 0);
        employedAt = new ArrayList<>();
    }

    /**
     * Copy constructor.
     * @param p Represents the employee to duplicate
     */
    public WarehouseEmployee (Person p) {
        super(p);
        employedAt = new ArrayList<>();
    }

    /**
     * Hires an employee to a service.
     * @param service Represents the service the employee is hired by
     */
    public void hiredAt(String service) {
        employedAt.add(service);
    }

    /**
     * Fires an employee from a service.
     * @param service Represents where the employee is fired from
     */
    public void firedAt(String service) {
        employedAt.remove(service);
    }

    /**
     * Getter for the employedAt variable.
     * @return Returns the employedAt variable
     */
    public ArrayList<String> workingAt() {
        return employedAt;
    }

    /**
     * Checks to see if this is the only place that a person works
     * used in collection class for removing employees
     * @return true if they work at one location
     */
    public boolean singleEmployment() {
        return employedAt.size() == 1;
    }

    @Override
    public String toString() {
        String companies = "";
        int counter = 0;
        for (String s : employedAt) {
            if (counter < employedAt.size() - 1) {
                companies += "&> " + s + "\n";
            } else {
                companies += "&> " + s;
            }
            counter++;
        }
        return super.toString() + "\nemployee is working at:\n" + companies;
    }
}
