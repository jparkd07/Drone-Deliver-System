import java.util.TreeMap;
import java.util.GregorianCalendar;

/**
 * This class represents a person who can occupy a variety of roles.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Person {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final GregorianCalendar birthday;
    private final String address;
    private int experience;
    private String pilotsID;

    /**
     * Instantiates a Person object.
     * @param userName Represents the username of the person
     * @param firstName Represents the first name of the person
     * @param lastName Represents the last name of the person
     * @param birthday Represents the person's birthday
     * @param address Represents the person's address
     * @param pilotsID Represents the person's pilot ID
     * @param experience Represents the person's experience
     */
    public Person(String userName, String firstName, String lastName,
            GregorianCalendar birthday, String address, String pilotsID, int experience){
            this.userName = userName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthday = birthday;
            this.address = address;
            this.experience = experience;
            this.pilotsID = pilotsID;
           
    }

    /**
     * Instantiates a Person object.
     * pilotsID defaults to null.
     * experience defaults to 0.
     * @param userName Represents the username of the person
     * @param firstName Represents the first name of the person
     * @param lastName Represents the last name of the person
     * @param birthday Represents the person's birthday
     * @param address Represents the person's address
     */
    public Person(String userName, String firstName, String lastName,
                    GregorianCalendar birthday, String address) {
        this(userName, firstName,lastName, birthday, address, null, 0);
    }

    /**
     * Copy constructor.
     * @param p Represents the person to duplicate
     */
    public Person (Person p) {
        this(p.userName, p.firstName, p.lastName, p.birthday, p.address, p.pilotsID, p.experience);
    }

    /**
     * Demotes the warehouse employee into a person.
     * @param p Represents the person to be demoted
     */
    public Person (WarehouseEmployee p) {
        this(p.getUserName(), p.getFirstName(), p.getLastName(), p.getBirthday(), p.getAddress(), p.getPilotID(), p.getExperience());
    }

    /**
     * Demotes a manager into a person.
     * @param p Represents the person to be demoted
     * */
    public Person (Manager p) {
        this(p.getUserName(), p.getFirstName(), p.getLastName(), p.getBirthday(), p.getAddress(), p.getPilotID(), p.getExperience());
    }

    //The way to demote a Pilot into a person

    /**
     * Demotes a pilot into a person.
     * @param p Represents the person to be demoted
     */
    public Person (Pilot p) {
        this(p.getUserName(), p.getFirstName(), p.getLastName(), p.getBirthday(), p.getAddress(), p.getPilotID(), p.getExperience());
    }

    @Override
    public String toString() {
        String dateString = birthday.get(birthday.YEAR) + "-" + birthday.get(birthday.MONTH) + "-" + birthday.get(birthday.DAY_OF_MONTH);
        if (pilotsID != null) {
            return String.format("\n" +
                            "userID: %s, name: %s %s, birth date: %s" + ", address: %s \n" + "user has a pilot's license (%s) with %d successful flight(s)" ,
                    userName, firstName, lastName, dateString, address,pilotsID,experience);
        }
        return String.format("userID: %s, name: %s %s, birth date: %s" + ", address: %s ",
                userName, firstName, lastName, dateString, address);
    }

    /**
     * Getter for the userName variable.
     * @return Returns the userName variable
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter for the firstName variable.
     * @return Returns the firstName variable
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the lastName variable.
     * @return Returns the lastName variable
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for the birthday variable.
     * @return Returns the birthday variable
     */
    public GregorianCalendar getBirthday() {
        return birthday;
    }

    /**
     * Getter for the address variable.
     * @return Returns the address variable
     */
    public String getAddress() {
        return address;
    }

    /**
     * Updates the experience variable.
     * @param newExp Represents the new experience to be added
     */
    public void updateExperience(int newExp) {
        experience += newExp;
    }

    /**
     * Setter for the pilotsID variable
     * @param newID Represents the new ID for the pilotsID to be set to
     */
    public void setPilotID(String newID) {
        pilotsID = newID;
    }

    /**
     * Getter for the experience variable.
     * @return Returns the experience variable
     */
    public Integer getExperience() {
        return experience;
    }

    /**
     * Getter for the pilotsID variable.
     * @return Returns the pilotsID variable
     */
    public String getPilotID(){
        return pilotsID;
    }
    /*
    public static void fireWorkers(Person x) {
        Person temp = new Person(x.getUserName(),
             x.getFirstName(), x.getLastName(),
             x.getBirthday(), x.getAddress(),x.getPilotID(),x.getExperience());
        CollectionClass.personList.remove(x.getUserName());
        CollectionClass.personList.put(temp.getUserName(), temp);
    }*/
}
