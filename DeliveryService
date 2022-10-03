import java.util.Collection;
import java.util.TreeMap;
import java.util.Set;

/**
 * This class represents a delivery service that delivers ingredients to restaurants.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class DeliveryService {
    private String name;
    private Integer moneyEarned;
    //private TreeMap<Integer, Drone> dronesOwned = new TreeMap<>();
    private Location locatedAt;
    private Manager managedBy;
    private TreeMap<String, WarehouseEmployee> employeeList = new TreeMap<>();
    private TreeMap<String, Pilot> pilotList = new TreeMap<>();



    /**
     * Instantiates a DeliveryService object.
     * @param name Represents the name of the delivery service
     * @param moneyEarned Represents the money earned by the delivery service
     * @param locatedAt Represents the location of the delivery service
     */
    public DeliveryService(String name, Integer moneyEarned, Location locatedAt) {
        if (moneyEarned < 0) {
            moneyEarned = 0;
            System.out.println("ERROR:revenue_less_than_0_autocorrected_to_0");
        }
        this.name = name;
        this.moneyEarned = moneyEarned;
        this.locatedAt = locatedAt;
    }

    /**
     * Makes the delivery service money
     * @param quantity Represents the amount of money earned by the delivery service
     */
    public void makeMoney(Integer quantity) {
        moneyEarned += quantity;
    }

    /**
     * Displays the drones owned by the delivery service.
     */
    public void displayOurDrones() {
        Set <String> pilots = pilotList.keySet();
        for (String p : pilots) {
            Set<Integer> leaders = pilotList.get(p).getSwarmsControlled().keySet();
            for(Integer tag : leaders) {
                Swarm s = pilotList.get(p).getSwarm(tag);
                String followerSwarmPrinting = "";
                System.out.println(s.getLeader().toString() + "&> pilot:" + pilotList.get(p).getUserName());
                if (s.getFollowers().size() != 0) {
                    System.out.print("drone is directing this swarm: [ drone tags");
                    for (Drone d : s.getFollowers()) {
                        System.out.print(" | " + d.getDroneTag());
                        followerSwarmPrinting += d.toString();
                    }
                    System.out.println(" ]");
                    System.out.print(followerSwarmPrinting);
                }
            }
        }
        Set<Integer> s = CollectionClass.getUnassignedDrones(name).keySet();
        for (Integer tag : s) {
            System.out.print(CollectionClass.getDrone(name, tag));
        }
    }

    /**
     * Moves the drone inputted to the location inputted, updates spaces left at destination
     * and uses the correct amount of fuel
     * @param destination destination that the  drone is going to
     * @param currentDrone drone needing to be moved
     */
    public void fly(Location destination, Drone currentDrone) {
        currentDrone.getCurrentLoc().updateSpacesLeft(-1);
        destination.updateSpacesLeft(1);
        currentDrone.useFuel(currentDrone.getCurrentLoc().calcDistance(destination));
        currentDrone.setLocation(destination);
    }

    public void flySingleDrone(Location destination, Integer drone_tag) {
        Drone d = CollectionClass.getUnassignedDrones(name).get(drone_tag);
        fly(destination, d);
        System.out.println("OK:change_completed");
    }

    public Pilot getPilotThatControlsLeader(Drone d) {
        for (Pilot p: pilotList.values()) {
            if (p.getSwarmsControlled().containsKey(d.getDroneTag())) {
                return p;
            }
        }
        return null;
    }

    //returns true if the Person works at this delivery service as an employee

    /**
     * Determines if the specified person is a warehouse employee of the delivery service.
     * @param username Represents the username of the person desired
     * @return Returns a boolean value determining if the person works at the delivery service as a warehouse employee
     */
    public boolean areTheyAnEmployee(String username) {
        if (employeeList.containsKey(username)) {
            return true;
        } else if (pilotList.containsKey(username)) {
            return true;
        }
        return false;
    }

    public void addToEmployeeList (String username) {
        WarehouseEmployee newEmployee;
        newEmployee = CollectionClass.getEmployee(username);
        employeeList.put(username, newEmployee);
        //newEmployee.hiredAt(name);
        System.out.println(newEmployee);
    }

    /**
     * Determines if the specified person works at the delivery service
     * @param username Represents the username of the person desired
     * @return Returns a boolean value determining if the person works at the delivery service
     */
    public boolean doTheyWorkHere(String username) {
        if(areTheyAnEmployee(username)) {
            return true;
        } else if(pilotList.containsKey(username)) {
            return true;
        } else if(!(managedBy == null) && managedBy.getUserName().equals(username)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true is the inputted username is a pilot for the delivery service
     * @param username username that is being checked
     * @return true if username is a current pilot
     */
    public boolean isPilot(String username) {
        return pilotList.containsKey(username);
    }

    /**
     * Assume all error checks have worked correctly, especially that the username is not already a
     * pilot somewhere else and that he has a pilotID
     * @param username username of pilot being added
     * @param droneTag the drone that he is controlling
     */
    public void hirePilot(String username, Integer droneTag) {
        Pilot newPilot;
        Swarm newSwarm;
        if (CollectionClass.isDroneSwarmLeader(name, droneTag)) {
            newSwarm = CollectionClass.getSwarm(name, droneTag);
            getPilotFromSwarm(droneTag).removeSwarm(newSwarm);
        } else {
            newSwarm = new Swarm(CollectionClass.getDrone(name, droneTag));
            CollectionClass.addSwarm(name, droneTag, newSwarm);
        }
        if (isPilot(username)) {
            newPilot = pilotList.get(username);
        } else {
            newPilot = new Pilot(CollectionClass.getPerson(username), name, newSwarm);
            CollectionClass.updatePerson(username, newPilot);
            newPilot.setEmployment(name);
            if (employeeList.containsKey(username)) {
                employeeList.remove(username);
            }
            pilotList.put(username, newPilot);
        }
        pilotList.get(username).addSwarm(newSwarm);
        System.out.println("OK:employee_has_been_appointed_pilot");
    }

    public Pilot getPilotFromSwarm(Integer leaderTag) {
        Set <String> pilots = pilotList.keySet();
        for (String p : pilots) {
            if(pilotList.get(p).getSwarmsControlled().containsKey(leaderTag)) {
                return pilotList.get(p);
            }
        }
        return null;
    }

    /**
     * updates the pilotsID and experience amount for a future pilot
     * NOT SURE IF PILOTS CAN GET RETRAINED OR IF MANAGERS CAN GET TRAINED, BUT HERE THEY DO
     * @param username username of the pilot that we are updatin
     * @param pilotsID the new id for the new pilot being trained
     * @param experience the experience gained during training
     */
    public void trainPilot(String username, String pilotsID, Integer experience) {
        if(employeeList.containsKey(username)) {
            employeeList.get(username).updateExperience(experience);
            employeeList.get(username).setPilotID(pilotsID);
        } else if (pilotList.containsKey(username)){
            pilotList.get(username).updateExperience(experience);
            pilotList.get(username).setPilotID(pilotsID);
        } else if (managedBy.getUserName().equals(username)) {
            managedBy.updateExperience(experience);
            managedBy.setPilotID(pilotsID);
        }
        System.out.println("OK:pilot_has_been_trained");
    }

    /**
     * Assumes that error checking works correctly, then checks to see if the employee is already
     * employeed somewhere else, and if they are then it gets that object and puts it in
     * this treemap, if not it creates a new warehouse employee to add
     * @param username the username of the person the delivery service is hiring
     */
    public void hireEmployee(String username) {
        WarehouseEmployee newEmployee;
        if(!CollectionClass.isEmployee(username)) {
            newEmployee = new WarehouseEmployee(CollectionClass.getPerson(username));
            CollectionClass.updatePerson(username, newEmployee);
        } else {
            newEmployee = CollectionClass.getEmployee(username);
        }
        employeeList.put(username, newEmployee);

        newEmployee.hiredAt(name);
        System.out.println("OK:employee_has_been_hired");
    }
    public void hirefiredEmployee(String username) {
        WarehouseEmployee newEmployee;
        if (!CollectionClass.isEmployee(username)) {
            newEmployee = new WarehouseEmployee(CollectionClass.getPerson(username));
            CollectionClass.updatePerson(username, newEmployee);
        } else {
            newEmployee = CollectionClass.getEmployee(username);
        }
        employeeList.put(username, newEmployee);

        newEmployee.hiredAt(name);
    }

    /**
     * Checks to see what type of employee they are (warehouse, pilot, manager) and calls the appropriate helper method.
     * @param username Represents the username of the worker to be fired
     */
    public void fireWorker(String username) {
        if(areTheyAnEmployee(username)) {
            fireEmployee(username);
        } else if(pilotList.containsKey(username)) {
            firePilot(username);
        }
        System.out.println("OK:employee_has_been_fired");
    }

    /**
     * Private helper method for the fireworker method specific to firing an employee
     * removes the employee from the ds list, removes the ds name from the employee hired at list
     * and removes the employee as a employee and resets it as a person
     * @param firedEmployee the employee that is being fired
     */
    private void fireEmployee(String firedEmployee) {
        if(employeeList.get(firedEmployee).singleEmployment()) {
            CollectionClass.removeEmployee(firedEmployee);
        }
        employeeList.get(firedEmployee).firedAt(name);
        employeeList.remove(firedEmployee);
    }

    /**
     * Private helper method for the fireworker method specific to firing a pilot
     * @param firedPilot username of the pilot being fired
     */
    public void firePilot(String firedPilot) {
        pilotList.get(firedPilot).setEmployment(null);
        pilotList.remove(firedPilot);
        CollectionClass.removeEmployee(firedPilot);
        CollectionClass.getDeliveryService(name).hirefiredEmployee(firedPilot);
    }

    /**
     * Precondition:
     * Assume that all the error checks work, especially the person is not a manager elsewhere, and if the person works there
     * and works only at that place
     * @param username username of the person who needs to be upgraded
     */
    public void setManager(String username) {
        fireEmployee(username);
        Manager newManager = new Manager(CollectionClass.getPerson(username), name);
        CollectionClass.updatePerson(username, newManager);
        newManager.setEmployment(getName());
        if(hasManager()) {
            CollectionClass.removeEmployee(managedBy.getUserName());
            this.hirefiredEmployee(managedBy.getUserName());
            managedBy = newManager;
        } else {
            managedBy = newManager;
        }
        System.out.println("OK:employee_has_been_appointed_manager");
    }

    /**
     * Getter for the managedBy variable.
     * @return Returns the managedBy variable
     */
    public Manager getManagedBy(){
        return managedBy;
    }

    /**
     * Determines if the delivery service has a manager.
     * @return Returns a boolean value indicating if the delivery service has a manager
     */
    public boolean hasManager() {
        return !(managedBy == null);
    }

    /*
    public void addDrone(Drone newDrone) {
        dronesOwned.put(newDrone.getDroneTag(), newDrone);
    }*/

    /*public void addSwarm(Swarm newSwarm) {
        swarmsOwned.put(newSwarm.getLeader(), newSwarm);
    }*/

    @Override
    public String toString() {
        return String.format("name: %s, revenue: $%d, location: %s", name, moneyEarned, locatedAt.getName());
    }

    /*
    public TreeMap<Integer, Drone> getDronesOwned() {
        return dronesOwned;
    }*/

    /**
     * Getter for the located at variable.
     * @return Returns the locatedAt variable
     */
    public Location getLoc() {
        return locatedAt;
    }

    /**
     * Gets an employee that works at the delivery service.
     * @param username Represents the employee to be retrieved
     * @return Returns the employee desired
     */
    public WarehouseEmployee getEmployee(String username) {
        return employeeList.get(username);
    }

    public TreeMap<String, Pilot> getPilotList() {
        return pilotList;
    }

    /**
     * Getter for the name variable.
     * @return Returns the name variable
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the delivery service has any employees.
     * @return Returns a boolean value determining if the delivery service has any employees
     */
    public boolean hasEmployee() {
        //null check
        return employeeList == null || employeeList.size() > 0;
    }

    public boolean hasWarehouseEmployee() {
        return !employeeList.isEmpty();
    }

    /**
     * Gets a pilot using the person's username.
     * @param username Represents the username to be specified
     * @return
     */
    public Pilot getPilot(String username) {
        return pilotList.get(username);
    }

    /**
     * Implementation:
     * First checks all the unassigned drones from the delivery services, and collects their money as well as
     * resets their money.
     * Next it iterates through all the swarms owned by the delivery service, calculates the amount that swarm
     * has made, and adds it to the revenue, as well as resets the money earned by that swarm.
     */
    public void collectRevenue() {
        Set<Integer> unassignedSet = CollectionClass.getUnassignedDrones(name).keySet();
        for (Integer tag : unassignedSet) {
           Drone d =  CollectionClass.getDrone(name, tag);
           if (d.getCurrentLoc().equals(locatedAt)) {
               this.moneyEarned += d.getMoneyEarned();
               d.resetMoney();
           }
        }
        Set<Integer> swarmSet = CollectionClass.getSwarms(name).keySet();
        for (Integer tag : swarmSet) {
           Swarm s =  CollectionClass.getSwarm(name, tag);
           if (s.getLoc().equals(locatedAt)) {
               this.moneyEarned += s.collectRevenue();
           }
        }
        System.out.println("OK:change_completed");

        //INSTEAD OF CHECKING THROUGH PILOTS LIKE BELOW, ABOVE CHECKS BY THE SWARM TREEMAP
        /*
        //check piloted drones
        for (Pilot p : pilotList) {
            Iterator<Swarm> pilotedSwarmIterator = p.getSwarmsControlled().values().iterator();
            while (pilotedSwarmIterator.hasNext()) {
                Swarm s = pilotedSwarmIterator.next();
                Drone d =  s.getLeader();
                if (d.getCurrentLoc().equals(locatedAt)) {
                    int money_leader = d.getMoneyEarned();
                    this.moneyEarned += money_leader;
                    d.resetMoney();
                }
                for (Drone drone : s.getFollowers()) {
                    drone =  s.getLeader();
                    if (drone.getCurrentLoc().equals(locatedAt)) {
                        int money = drone.getMoneyEarned();
                        this.moneyEarned += money;
                        drone.resetMoney();
                    }
                }
            }
        }*/
    }
}
