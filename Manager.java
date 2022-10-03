import java.util.GregorianCalendar;

/**
 * This class represents a manager that is appointed by a service.
 * It is a child class of Person.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Manager extends Person {
    private String employedAt;

    /**
     * Instantiates a Manager object.
     * @param userName Represents the username of the manager
     * @param firstName Represents the first name of the manager
     * @param lastName Represents the last name of the manager
     * @param birthday Represents the manager's birthday
     * @param address Represents the manager's address
     * @param employedAt Represents the service the manager is employed at
     * @param pilotsID Represents the pilot ID of the manager
     * @param experience Represents the experience of the manager
     */
    public Manager (String userName, String firstName, String lastName,
                    GregorianCalendar birthday, String address, String employedAt, String pilotsID, int experience) {
        super(userName, firstName, lastName, birthday, address,  pilotsID, experience);
        this.employedAt = employedAt;
    }

    /**
     * Instantiates a Manager object.
     * @param userName Represents the username of the manager
     * @param firstName Represents the first name of the manager
     * @param lastName Represents the last name of the manager
     * @param birthday Represents the manager's birthday
     * @param address Represents the manager's address
     * @param employedAt Represents the service the manager is employed at
     */
     public Manager (String userName, String firstName, String lastName,
            GregorianCalendar birthday, String address, String employedAt){
                this(userName, firstName, lastName, birthday, address, employedAt, null, 0);
    }

    /**
     * Copy constructor.
     * @param p Represents the person to duplicate
     * @param service Represents the service the manager is employed at
     */
    public Manager (Person p, String service) {
        super(p);
        employedAt = service;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nemployee is managing: %s", employedAt);
    }

    /**
     * Determines if the manager is currently employed.
     * @return Returns a boolean value determining if the manager is currently employed
     */
    public boolean currentlyEmployed() {
        return !(employedAt == null);
    }

    /**
     * Setter for the employedAt variable
     * @param service Represents where the manager is employed at
     */
    public void setEmployment(String service) {
        employedAt = service;
    }
}
