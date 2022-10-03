import java.util.Iterator;
import java.util.TreeMap;
import java.util.Set;

/**
 * This abstract collection class contains the collections of objects in the system.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public abstract class CollectionClass {
    private static TreeMap<String, Location> locationList = new TreeMap<>();
    private static TreeMap<String, DeliveryService> deliveryServiceList = new TreeMap<>();
    private static TreeMap<String, Restaurant> restaurantList = new TreeMap<>();
    private static TreeMap<String, Ingredient> ingredientList = new TreeMap<>();
    public static TreeMap<String, TreeMap<Integer, Swarm>> swarmList = new TreeMap<>();
    public static TreeMap<String, TreeMap<Integer, Drone>> unassignedDroneList = new TreeMap<>();
    private static TreeMap<String, Person> personList = new TreeMap<>();
    //private static TreeMap<String, TreeMap<String, Pilot>> pilotList = new TreeMap<>();
    //private static TreeMap<String, TreeMap<String, Manager>> managerList = new TreeMap<>();
    //private static TreeMap<String, WarehouseEmployee> employeeList = new TreeMap<>();

    /**
     * Gets a location from its list.
     * @param name Represents the name of the desired location.
     * @return Returns the specified location.
     */
    public static Location getLocation(String name) {
        return locationList.get(name);
    }

    /**
     * Getter for the swarmList variable.
     * @return Returns the swarmList variable
     */
    public static TreeMap<String, TreeMap<Integer, Swarm>> getSwarmList() {
        return swarmList;
    }

    /**
     * Gets a delivery service from its list.
     * @param name Represents the name of the desired service.
     * @return Returns the specified delivery service.
     */
    public static DeliveryService getDeliveryService(String name) {
        return deliveryServiceList.get(name);
    }

    /**
     * Gets a restaurant from its list.
     * @param name Represents the name of the desired restaurant.
     * @return Returns the specified restaurant.
     */
    public static Restaurant getRestaurant(String name) {
        return restaurantList.get(name);
    }

    /**
     * Gets an ingredient from its list.
     * @param barcode Represents the name of the desired ingredient.
     * @return Returns the specified ingredient.
     */
    public static Ingredient getIngredient(String barcode) {
        return ingredientList.get(barcode);
    }

    /**
     * Gets a person from its list.
     * @param username Represents the username of the desired person
     * @return Returns the desired person
     */
    public static Person getPerson(String username) {
        return personList.get(username);
    }

    /**
     * Finds a drone from a delivery service, looks through both the swarmList and the unassigned drone list.
     * @param dsname name of the delivery service you are looking for the drone in
     * @param droneTag tag of the drone you are looking for
     * @return the drone you are looking for, returns null if the drone does not exist in that delivery service
     */
    public static Drone getDrone(String dsname, Integer droneTag) {
        if(unassignedDroneList.get(dsname).containsKey(droneTag)) {
            return unassignedDroneList.get(dsname).get(droneTag);
        } else {
            Set<Integer> set = swarmList.get(dsname).keySet();
            for (Integer tag : set) {
                if(swarmList.get(dsname).get(tag).checkDrone(droneTag)) {
                    return swarmList.get(dsname).get(tag).getDrone(droneTag);
                }
            }
        }
        return null;
    }

    /**
     * Returns the swarm given the drone tag of the leader drone.
     * @param dsname name of the service that owns the swarm
     * @param leaderTag tag of the leader drone
     * @return the swarm that you want
     */
    public static Swarm getSwarm(String dsname, Integer leaderTag) {
        return swarmList.get(dsname).get(leaderTag);
    }

    /**
     * Gets a delivery service's location.
     * @param dsname Represents the name of the delivery service
     * @return Returns a delivery service's location
     */
    public static Location getDeliveryServiceLoc(String dsname) {
        return deliveryServiceList.get(dsname).getLoc();
    }

    /**
     * Gets a delivery service's unassigned drones
     * @param dsname Represents the name of the delivery service
     * @return Returns a delivery service's unassigned drones
     */
    public static TreeMap<Integer, Drone> getUnassignedDrones(String dsname) {
        return unassignedDroneList.get(dsname);
    }

    /**
     * Gets a delivery service's swarms
     * @param dsname Represents the name of the delivery service
     * @return Returns a delivery service's swarms
     */
    public static TreeMap<Integer, Swarm> getSwarms(String dsname) {
        return swarmList.get(dsname);
    }

    /**
     * Assume that all precondition checks work correctly, especially that the employee exists somewhere.
     * Returns a warehouseEmployee from the employeeList of a delivery service.
     * @param username Represents the username of the employee
     * @return the warehouseEmployee object from a delivery service
     */
    public static WarehouseEmployee getEmployee(String username) {
        Set<String> serviceNames = deliveryServiceList.keySet();
        for (String names : serviceNames) {
            if (deliveryServiceList.get(names).areTheyAnEmployee(username)) {
                return deliveryServiceList.get(names).getEmployee(username);
            }
        }
        return null;
    }

    /**
     * Adds a location to its list.
     * @param name Represents the name of the location
     * @param loc Represents the location itself
     */
    public static void addLocation(String name, Location loc) {
        locationList.put(name, loc);
        System.out.println("OK:location_created");
    }

    /**
     * Adds an ingredient to its list.
     * @param barcode Represents the barcode of the ingredient
     * @param ing Represents the ingredient itself
     */
    public static void addIngredient(String barcode, Ingredient ing) {
        ingredientList.put(barcode, ing);
        System.out.println("OK:ingredient_created");
    }

    /**
     * Adds a delivery service to its list.
     * @param name Represents the name of the delivery service
     * @param ds Represents the delivery service itself
     */
    public static void addDeliveryService(String name, DeliveryService ds) {
        deliveryServiceList.put(name, ds);
        //Creates places to put drones in the drone treemaps for this delivery service
        unassignedDroneList.put(ds.getName(), new TreeMap<Integer, Drone>());
        swarmList.put(ds.getName(), new TreeMap<Integer, Swarm>());
        System.out.println("OK:delivery_service_created");
    }

    /**
     * Adds a restaurant to its list.
     * @param name Represents the name of the restaurant
     * @param res Represents the restaurant itself
     */
    public static void addRestaurant(String name, Restaurant res) {
        restaurantList.put(name, res);
        System.out.println("OK:restaurant_created");
    }

    /**
     * Adds a person to its list.
     * @param username Represents the username of the person
     * @param p Represents the person itself
     */
    public static void addPerson(String username, Person p) {
        personList.put(username, p);
        System.out.println("OK:person_created");
    }

    /*public static void addPilot(String dsname, Person p) {
        //in case the delivery service does not exist in the treemap yet
        if(!employeeList.containsKey(dsname)) {
            employeeList.put(dsname, new TreeMap<String, WarehouseEmployee>());
        }
        employeeList.get(dsname).put(p.getUserName(), new WarehouseEmployee(p));
        System.out.println("OK:person_created");
    }*/

    /**
     * Adds a drone to the treemap of delivery service name, drone treemap for unassigned drones.
     * @param dsname name of the delivery serivce
     * @param droneTag the tag of the drone to be added used in interior treemap
     * @param drone the drone to be added
     * Prints a success statement after the changes have gone into effect
     */
    public static void addUnassignedDrone(String dsname, Integer droneTag, Drone drone) {
        unassignedDroneList.get(dsname).put(droneTag, drone);

        System.out.println("OK:drone_created");
    }

    /**
     * Adds a swarm to the treemap: delivery service name, swarm treemap: leadertag, swarm
     * @param dsname name of delivery service
     * @param leaderDroneTag tag of the leader drone
     * @param swarm swarm to be added
     * Prints a success statement after the changes have gone into effect
     */
    public static void addSwarm(String dsname, Integer leaderDroneTag, Swarm swarm) {
        swarmList.get(dsname).put(leaderDroneTag, swarm);
        unassignedDroneList.get(dsname).remove(leaderDroneTag);
        //System.out.println("OK:swarm_created");
    }

    /**
     * Will remove newDrone from the unassignedDrone list and will add it to a swarm in the correct
     * treemap of delivery services and swarms, and will call the method in swarm to addDrone to itself.
     * @param dsname delivery service that owns the swarms and drones
     * @param leaderDroneTag tag of the leader drone
     * @param newDroneTag the drone being added
     */
    public static void addDroneToSwarm(String dsname, Integer leaderDroneTag, Integer newDroneTag) {
        if (isDroneSwarmLeader(dsname, newDroneTag)) {
            deliveryServiceList.get(dsname).getPilotFromSwarm(newDroneTag).removeSwarm(swarmList.get(dsname).get(newDroneTag));
            unassignedDroneList.get(dsname).put(newDroneTag, swarmList.get(dsname).get(newDroneTag).getLeader());
            swarmList.get(dsname).remove(newDroneTag);
        }
        swarmList.get(dsname).get(leaderDroneTag).addDroneToSwarm(unassignedDroneList.get(dsname).get(newDroneTag));
        unassignedDroneList.get(dsname).remove(newDroneTag);
        System.out.println("OK:swarm_joined");
    }

    /**
     * Assume the person is an employee and works for that service
     * Removes the employee from the employee list, and will demote them to a Person in personList, if 
     * thats the employees only place of employement
     * @param username username of person needing removing
     */
    public static void removeEmployee(String username) {
        personList.put(username, new Person(getPerson(username)));
    }

    /**
     * Precondtions:
     * Assume happy path, that everything given exists and drone belongs to the delivery
     * service and the drone is already part of the swarm
     * 
     * Implementation:
     * Loops through all the swarms owned by that delivery service to find the drone that needs
     * to be removed, and removes it from the treemaps, as well as calls remove on the swarm
     * @param service_name name of delivery service
     * @param swarm_drone_tag leader of the swarm
     */
    public static void leaveSwarm(String service_name, Integer swarm_drone_tag) {
        Iterator<Swarm> swarmsOwned = swarmList.get(service_name).values().iterator();
        while (swarmsOwned.hasNext()) {
            Swarm s = swarmsOwned.next();
            if (s.checkDrone(swarm_drone_tag)) {
                if(s.getLeader().getDroneTag().equals(swarm_drone_tag)) {
                    if(s.getFollowers().size() != 0) {
                        System.out.println("ERROR:drone_leading_other_drones");
                    } else {
                        Drone d = s.getDrone(swarm_drone_tag);
                        unassignedDroneList.get(service_name).put(swarm_drone_tag, d);
                        swarmList.get(service_name).remove(swarm_drone_tag);
                        System.out.println("OK:change_completed");
                    }
                } else {
                    Drone d = s.getDrone(swarm_drone_tag);
                    unassignedDroneList.get(service_name).put(swarm_drone_tag, d);
                    s.removeDroneFromSwarm(d);
                    Pilot pilotUser = deliveryServiceList.get(service_name).getPilotFromSwarm(s.getLeader().getDroneTag());
                    deliveryServiceList.get(service_name).hirePilot(pilotUser.getUserName(), swarm_drone_tag);
                    System.out.println("OK:change_completed");
                    break;
                }
            }
        }
    }

    /**
     * Loads an ingredient onto a drone.
     * @param dsname Represents the name of the delivery service
     * @param droneTag Represents the identifier of the drone
     * @param barcode Represents the identifier of the ingredient
     * @param quantity Represents the amount of the ingredient
     * @param price Represents the price of the ingredient
     */
    public static void loadIngredient(String dsname, Integer droneTag, String barcode, Integer quantity, Integer price) {
        getDrone(dsname, droneTag).loadPayload(new Payload(CollectionClass.getIngredient(barcode), quantity, price));
        System.out.println("OK:change_completed");
    }

    /**
     * Updates a list to include the correct occupation of a person.
     * @param username Represents the username of the person
     * @param p Represents the occupation of the person
     */
    public static void updatePerson(String username, WarehouseEmployee p) {
        personList.put(username, p);
    }

    /**
     * Updates a list to include the correct occupation of a person.
     * @param username Represents the username of the person
     * @param p Represents the occupation of the person
     */
    public static void updatePerson(String username, Pilot p) {
        personList.put(username, p);
    }

    /**
     * Updates a list to include the correct occupation of a person.
     * @param username Represents the username of the person
     * @param p Represents the occupation of the person
     */
    public static void updatePerson(String username, Manager p) {
        personList.put(username, p);
    }

    /**
     * Displays all persons.
     */
    public static void displayPersons() {
        Set<String> s = personList.keySet();
        for (String i : s) {
            System.out.println(personList.get(i));
        }
        System.out.println("OK:display_completed");
    }

    /**
     * Displays all restaurants.
     */
    public static void displayRestaurants() {
        Set<String> set = restaurantList.keySet();
        for (String key : set) {
        System.out.println(restaurantList.get(key));
        }
        System.out.println("OK:display_completed");
    }

    /**
     * Displays all delivery services.
     */
    public static void displayDeliveryServices() {
        Set<String> set = deliveryServiceList.keySet();
        for (String key : set) {
            System.out.println(deliveryServiceList.get(key));
        }
        System.out.println("OK:display_completed");
    }

    /**
     * Displays all locations.
     */
    public static void displayLocations() {
        Set<String> set = locationList.keySet();
        for (String key : set) {
            System.out.println(locationList.get(key));
        }
        System.out.println("OK:display_completed");
    }

    /**
     * Displays all ingredients.
     */
    public static void displayIngredients() {
        Set<String> set = ingredientList.keySet();
        for (String key : set) {
            System.out.println(ingredientList.get(key).toString());
        }
        System.out.println("OK:display_completed");
    }

    /**
     * Displays all drones owned by a particular delivery service.
     * @param dsname Represents the name of the delivery service
     */
    public static void displayDrones(String dsname) {
        if (deliveryServiceList.containsKey(dsname)) {
            deliveryServiceList.get(dsname).displayOurDrones();
            System.out.println("OK:display_completed");
        } else {
            System.out.println("ERROR:delivery_service_does_not_exist");
        }
    }

    /**
     * Displays all drones.
     */
    public static void displayAllDrones() {
        Set<String> set = deliveryServiceList.keySet();
        for (String key : set) {
            System.out.println(String.format("service name [%s] drones:", deliveryServiceList.get(key).getName()));
            deliveryServiceList.get(key).displayOurDrones();
        }
        System.out.println("OK:display_completed");
    }

    /**
     * Checks if a delivery service exists.
     * @param service_name Represents the delivery service to be checked
     * @return Returns a boolean value determining if the delivery service exists
     */
    public static boolean checkDeliveryService(String service_name) {
        return deliveryServiceList.containsKey(service_name);
    }

    /**
     * Checks if a restaurant exists.
     * @param name Represents the restaurant to be checked
     * @return Returns a boolean value determining if the restaurant exists
     */
    public static boolean checkRestaurantList(String name) {
        return restaurantList.containsKey(name);
    }

    /**
     * Checks if an ingredient exists.
     * @param barcode Represents the ingredient to be checked
     * @return Returns a boolean value determining if the ingredient exists
     */
    public static boolean checkIngredientList(String barcode){
        return ingredientList.containsKey(barcode);
    }

    /**
     * Checks if a person exists.
     * @param username Represents the person to be checked
     * @return Returns a boolean value determining if the person exists
     */
    public static boolean checkPersonList(String username){
        return personList.containsKey(username);
    }

    /**
     * Checks if a location exists.
     * @param name Represents the location to be checked
     * @return Returns a boolean value determining if the location exists
     */
    public static boolean checkLocationList(String name){
        return locationList.containsKey(name);
    }

    /**
     * Checks if a person is a manager.
     * @param user_name Represents the person to be checked
     * @return Returns a boolean value determining if the person is a manager
     */
    public static boolean isManaging(String user_name) {
        Set<String> ds = deliveryServiceList.keySet();
        for (String service: ds) {
            if (deliveryServiceList.get(service).getManagedBy() != null) {
                 if(deliveryServiceList.get(service).getManagedBy().getUserName().equals(user_name)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks both the unassigned drone list and the swarm list to ensure that a 
     * given dronetag does not already exist within the treemap of drones
     * @param deliveryService the delivery service whose drones you are looking through
     * @param droneTag the tag of the drone that you are looking for
     * @return returns true if the drone already exists
     */
    public static boolean checkDroneList(String deliveryService, Integer droneTag) {
        if(unassignedDroneList.get(deliveryService).containsKey(droneTag)) {
            return true;
        }
        Set<Integer> set = swarmList.get(deliveryService).keySet();
        for (Integer tag : set) {
            if(swarmList.get(deliveryService).get(tag).checkDrone(droneTag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a set of coordinates are occupied.
     * @param x Represents the x coordinate
     * @param y Represents the y coordinate
     * @return Returns a boolean value determining if the coordinates are occupied
     */
    public static boolean checkCoordinatesOccupied (int x, int y) {
        Set <String> locName = locationList.keySet();
        for (String name: locName){
            if (locationList.get(name).getXCoord() == x && locationList.get(name).getYCoord() == y) {
                System.out.println("ERROR:location_already_exists_at_these_coordinates");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if the given dronetag is already the leader of a swarm, used in addDroneToSwarm in swarm class.
     * @param dsname name of the delivery service that is being checked
     * @param droneTag the tag of the drone being checked
     * @return true if the drone is already a swarm leader
     */
    public static boolean isDroneSwarmLeader(String dsname, Integer droneTag) {
        return swarmList.get(dsname).containsKey(droneTag);
    }

    /**
     * Checks to see if the person is classified as an employee
     * @param username username of person you are searching for
     * @return true if the person is an employee at a location
     */
    public static boolean isEmployee(String username) {
        Set<String> serviceNames = deliveryServiceList.keySet();
        for (String names : serviceNames) {
            if (deliveryServiceList.get(names).areTheyAnEmployee(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks through the pilot list of every delivery service to see
     * if a specific username is a pilot there
     * @param username username being searched for
     * @return true if they are a pilot somewhere
     */
    public static boolean isPilot(String username) {
        Set<String> serviceNames = deliveryServiceList.keySet();
        for (String names : serviceNames) {
            if (deliveryServiceList.get(names).isPilot(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a drone is in a swarm.
     * @param service_name Represents the service the drone belongs to
     * @param droneTag Represents the tag of the drone
     * @return Returns a boolean value determining if a drone is in a swarm.
     */
    public static boolean isDroneInSwarm(String service_name, Integer droneTag) {
        return (!unassignedDroneList.get(service_name).containsKey(droneTag));
    }

    /**
     * Checks how many spaces a location has left.
     * @param service_name Represents the name of the service
     * @return Returns a value determining how many spaces are left at the location
     */
    public static int remainingSpace(String service_name){
        return deliveryServiceList.get(service_name).getLoc().getRemaining();
    }

    /**
     * Checks if a person is in a person list.
     * @param user_name Represents the person desired
     * @return Returns a boolean value determining if the person is in the list
     */
    public static boolean inPersonList(String user_name) {
        return personList.containsKey(user_name);
    }
}
