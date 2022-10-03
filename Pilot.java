import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class represents a pilot that flies drones.
 * It is a child class of Person.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Pilot extends Person {
    private String employedAt;
    private TreeMap<Integer, Swarm> swarmsControlled = new TreeMap<Integer, Swarm>();

    /**
     * Instantiates a Pilot object.
     * @param userName Represents the username of the pilot
     * @param firstName Represents the first name of the person
     * @param lastName Represents the last name of the person
     * @param birthday Represents the person's birthday
     * @param address Represents the person's address
     * @param pilotID Represents the person's pilot ID
     * @param experience Represents the person's experience
     * @param employedAt Represents the place where the pilot is employed at
     */
    public Pilot(String userName, String firstName, String lastName,
                    GregorianCalendar birthday, String address, String pilotID, int experience, String employedAt) {
        super(userName, firstName, lastName, birthday, address, pilotID, experience);
        this.employedAt = employedAt;
    }

    /**
     * Instantiates a Pilot object using a person.
     * @param p Represents the person to be given pilot status
     * @param employedAt Represents the place where the pilot is employed at
     * @param swarm Represents the swarm of drones that the pilot controls
     */
    public Pilot(Person p, String employedAt, Swarm swarm) {
        super(p);
        this.employedAt = employedAt;
        swarmsControlled.put(swarm.getLeader().getDroneTag(), swarm);
    }

    @Override
    public String toString() {
        Set<Integer> swarmsControlledSet = swarmsControlled.keySet();
        String droneTags = "";
        for (Integer key : swarmsControlledSet) {
            droneTags += " | " + swarmsControlled.get(key).getLeader().getDroneTag();
            /*if (swarmsControlled.get(key).getFollowers() != null) {
                for (Drone follower : swarmsControlled.get(key).getFollowers()) {
                    droneTags += " | " + follower.getDroneTag();
                }
            }*/
        }
        if (swarmsControlled.isEmpty()) {
            return super.toString() + String.format("\nemployee is working at:\n&> %s\nuser has a pilot's license (%s) with %d " +
                            "successful flight(s)",
                    employedAt, super.getPilotID(), super.getExperience(), droneTags);
        } else {
            /*return super.toString() + String.format("employee is working at:\n&> %s\nuser has a pilot's simon license (%s) with %d " +
                            "successful flight(s)\nemployee is flying these drones: [ drone tags %s ]",
                    employedAt, super.getPilotID(), super.getExperience(), droneTags);*/
            return super.toString() + String.format("\nemployee is working at:\n&> %s\nemployee is flying these drones: [ drone tags%s ]",
                    employedAt, droneTags);
        }
    }

    /**
     * Determines if the pilot is currently employed.
     * @return Returns a boolean value indicating if the pilot is employed
     */
    public boolean currentlyEmployed() {
        return !(employedAt == null);
    }

    /**
     * Setter for the employedAt variable.
     * @param newEmployment Represents the new place where the pilot works
     */
    public void setEmployment(String newEmployment) {
        employedAt = newEmployment;
    }

    /**
     * Getter for the employedAt variable.
     * @return Returns the employedAt variable
     */
    public String getEmployedAt() {
        return employedAt;
    }
    
    //create functionality to return true if they are currently piloting drone

    /**
     * Determines if the pilot is currently piloting drones.
     * @return Returns a boolean value determining if the pilot is piloting drones
     */
    public boolean currentlyPiloting() {
        return !swarmsControlled.isEmpty();
    }

    /**
     * Getter for the swarmsControlled variable.
     * @return Returns the swarmsControlled variable
     */
    public TreeMap<Integer, Swarm> getSwarmsControlled() {
        return swarmsControlled;
    }

    /**
     * Adds a drone under the pilot's control
     * @param d Represents the drone to be added
     */
    public void addDrone(Drone d){
        Swarm s = new Swarm(d);
        swarmsControlled.put(d.getDroneTag(), s);
    }

    /**
     * Gets a swarm controlled by the pilot.
     * @param tag Represents the tag of the desired swarm
     * @return Returns the swarm desired
     */
    public Swarm getSwarm(Integer tag) {
        return swarmsControlled.get(tag);
    }

    /**
     * Adds a swarm to the control of the pilot.
     * @param s Represents the swarm to be added
     */
    public void addSwarm(Swarm s) {
        swarmsControlled.put(s.getLeader().getDroneTag(), s);
    }

    /**
     * Removes a swarm from the control of the pilot.
     * @param s Represents the swarm to be removed
     */
    public void removeSwarm(Swarm s) {
        swarmsControlled.remove(s.getLeader().getDroneTag());
        if(!currentlyPiloting()) {
            CollectionClass.getDeliveryService(employedAt).firePilot(getUserName());

        }
    }

}
