
 Enterprise

Pull requests
Issues
Explore
 
@jpark3068 
ipatel43
/
CS2340-Group-15
Private
 Watch 2 
Fork 2
 Star 0
Code
Issues
Pull requests
Actions
Projects
Wiki
Security
Insights
 main 
CS2340-Group-15/InterfaceLoop.java
Go to file

@dsehgal34
dsehgal34 changes
Latest commit 8f7db27 on Jul 16
 History
 5 contributors
@tgavaletz3 @dsehgal34 @skamat36 @ipatel43 @jpark3068
402 lines (369 sloc)  17.7 KB
Raw Blame
     
import java.util.*;
/**
 * This class represents the interface the user interacts with and serves as the controller.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class InterfaceLoop {

    /**
     * Makes an ingredient.
     * @param init_barcode Represents the barcode of the ingredient
     * @param init_name Represents the name of the ingredient
     * @param init_weight Represents the weight of the ingredient
     */
    void makeIngredient(String init_barcode, String init_name, Integer init_weight) {
        if(ErrorHandle.makeIngredient(init_barcode,init_name,init_weight)){
            CollectionClass.addIngredient(init_barcode, new Ingredient(init_barcode, init_weight, init_name));
        }
    }

    /**
     * Displays all ingredients.
     */
    void displayIngredients() {
        CollectionClass.displayIngredients();
    }

    /**
     * Makes a location.
     * @param init_name Represents the name of the location
     * @param init_x_coord Represents the x coordinate of the location
     * @param init_y_coord Represents the y coordinate of the location
     * @param init_space_limit Represents the space limit of the location
     */
    void makeLocation(String init_name, Integer init_x_coord, Integer init_y_coord, Integer init_space_limit) {
        if (ErrorHandle.makeLocation(init_name,init_x_coord,init_y_coord,init_space_limit)){
            CollectionClass.addLocation(init_name, new Location(init_name, init_x_coord, init_y_coord, init_space_limit));
        }
    }

    /**
     * Displays all locations.
     */
    void displayLocations() {
        CollectionClass.displayLocations();
    }

    /**
     * Checks the distance of drone travel.
     * @param departure_point Represents the drone's departure point
     * @param arrival_point Represents the drone's arrival point
     */
    void checkDistance(String departure_point, String arrival_point) {
        if (!(CollectionClass.checkLocationList(departure_point) || CollectionClass.checkLocationList(arrival_point))) {
            System.out.println("ERROR:location_not_found");
        } else {
            System.out.println("OK:distance = " + CollectionClass.getLocation(departure_point).calcDistance(CollectionClass.getLocation(arrival_point)));
        }
    }

    /**
     * Makes a delivery service.
     * @param init_name Represents the name of the delivery service
     * @param init_revenue Represents the revenue of the delivery service
     * @param located_at Represents the location of the delivery service
     */
    void makeDeliveryService(String init_name, Integer init_revenue, String located_at) {
        if(ErrorHandle.makeDeliveryService(init_name,init_revenue,located_at)) {
            CollectionClass.addDeliveryService(init_name, new DeliveryService(init_name, init_revenue, CollectionClass.getLocation(located_at)));
        }
    }

    /**
     * Displays all services.
     */
    void displayServices() {
        CollectionClass.displayDeliveryServices();
    }

    /**
     * Makes a restaurant.
     * @param init_name Represents the name of the restaurant
     * @param located_at Represents the location of the restaurant
     */
    void makeRestaurant(String init_name, String located_at) {
        if (ErrorHandle.makeRestaurant(init_name,located_at)) {
            CollectionClass.addRestaurant(init_name, new Restaurant(init_name, 0, CollectionClass.getLocation(located_at)));
        }
    }

    /**
     * Displays all restaurants.
     */
    void displayRestaurants() {
        CollectionClass.displayRestaurants();
    }

    /**
     * Makes a drone.
     * @param service_name Represents the service the drone belongs to
     * @param init_tag Represents the identifier for the drone
     * @param init_capacity Represents the capacity of the drone
     * @param init_fuel Represents the fuel of the drone
     */
    void makeDrone(String service_name, Integer init_tag, Integer init_capacity, Integer init_fuel) {
        if (ErrorHandle.makeDrone(service_name, init_tag, init_capacity, init_fuel)) {
            CollectionClass.addUnassignedDrone(service_name, init_tag, new Drone(CollectionClass.getDeliveryServiceLoc(service_name), init_capacity, init_tag, init_fuel));
        }
    }

    /**
     * Displays drones for a particular service.
     * @param service_name Represents the service that drones are displayed from
     */
    void displayDrones(String service_name) {
        CollectionClass.displayDrones(service_name);
    }

    /**
     * Displays all drones.
     */
    void displayAllDrones() {
        CollectionClass.displayAllDrones();
    }

    /**
     * Flies a drone.
     * @param service_name Represents the service the drone belongs to
     * @param drone_tag Represents the identifier for the drone
     * @param destination_name Represents the destination for the drone
     */
    void flyDrone(String service_name, Integer drone_tag, String destination_name) {
        if (ErrorHandle.flyDrone(service_name, drone_tag, destination_name)) {
            //check if drone in swarm or not
            if (CollectionClass.isDroneInSwarm(service_name, drone_tag)) {
                CollectionClass.swarmList.get(service_name).get(drone_tag).flySwarm(CollectionClass.getLocation(destination_name), CollectionClass.getDeliveryService(service_name));
            } else {
                //CollectionClass.getDeliveryService(service_name).fly(CollectionClass.getLocation(destination_name), CollectionClass.getUnassignedDrones(service_name).get(drone_tag));
                CollectionClass.getDeliveryService(service_name).flySingleDrone(CollectionClass.getLocation(destination_name), drone_tag);
            }
        }
    }

    /**
     * Loads a payload onto a drone.
     * @param service_name Represents the service that the drone belongs to
     * @param drone_tag Represents the identifier for the drone
     * @param barcode Represents the barcode for the ingredient
     * @param quantity Represents the quantity of the ingredient
     * @param unit_price Represents the unit price of the ingredient
     */
    void loadIngredient(String service_name, Integer drone_tag, String barcode, Integer quantity, Integer unit_price) {
        if (ErrorHandle.loadIngredient(service_name, drone_tag, barcode, quantity, unit_price)) {
            CollectionClass.loadIngredient(service_name, drone_tag, barcode, quantity, unit_price);
        }
    }

    /**
     * Loads fuel onto a drone.
     * @param service_name Represents the service that the drone belongs to
     * @param drone_tag Represents the identifier for the drone
     * @param petrol Represents how much fuel to add
     */
    void loadFuel(String service_name, Integer drone_tag, Integer petrol) {
        if (ErrorHandle.loadFuel(service_name, drone_tag, petrol)) {
            CollectionClass.getDrone(service_name, drone_tag).loadFuel(petrol);
        }
    }

    /**
     * Purchases an ingredient.
     * @param restaurant_name Represents the restaurant that purchases the ingredient
     * @param service_name Represents the service that the drone belongs to
     * @param drone_tag Represents the identifier for the drone
     * @param barcode Represents the barcode for the ingredient
     * @param quantity Represents the quantity of the ingredient
     */
    void purchaseIngredient(String restaurant_name, String service_name, Integer drone_tag, String barcode, Integer quantity) {
        if (ErrorHandle.purchaseIngredient(restaurant_name, service_name, drone_tag, barcode, quantity)) {
            CollectionClass.getRestaurant(restaurant_name).purchaseIngredient(CollectionClass.getDeliveryService(service_name), CollectionClass.getDrone(service_name, drone_tag), CollectionClass.getIngredient(barcode), quantity);
        }
    }

    /**
     * Makes a person.
     * @param init_username Represents the username of the person
     * @param init_fname Represents the first name of the person
     * @param init_lname Represents the last name of the person
     * @param init_year Represents the year of the person's birthday
     * @param init_month Represents the month of the person's birthday
     * @param init_date Represents the date of the person's birthday
     * @param init_address Represents the address of the person
     */
    void makePerson(String init_username, String init_fname, String init_lname,
                    Integer init_year, Integer init_month, Integer init_date, String init_address) {
        if(ErrorHandle.makePerson(init_username,init_fname,init_lname,init_year,init_month,init_date,init_address)) {
            CollectionClass.addPerson(init_username, new Person(init_username, init_fname, init_lname, 
                new GregorianCalendar(init_year, init_month, init_date), init_address));
        }

    }

    /**
     * Displays all persons.
     */
    void displayPersons() {
        CollectionClass.displayPersons();
    }

    /**
     * Hires a worker.
     * @param service_name Represents the service that hires the worker
     * @param user_name Represents the username of the person to be hired
     */
    void hireWorker(String service_name, String user_name) {
        if (ErrorHandle.hireWorker(service_name, user_name)) {
            CollectionClass.getDeliveryService(service_name).hireEmployee(user_name);
        }
    }

    /**
     * Fires a worker.
     * @param service_name Represents the service that fires the worker
     * @param user_name Represents the username of the person to be fired
     */
    void fireWorker(String service_name, String user_name) {

        if (ErrorHandle.fireWorker(service_name, user_name)) {
            CollectionClass.getDeliveryService(service_name).fireWorker(user_name);
        }
    }

    /**
     * Appoints a manager
     * @param service_name Represents the service that appoints a manager
     * @param user_name Represents the username of the person to be appointed
     */
    void appointManager(String service_name, String user_name) {
        if (ErrorHandle.appointManager(service_name, user_name)) {
            CollectionClass.getDeliveryService(service_name).setManager(user_name);
        }
    }

    /**
     * Trains a pilot.
     * @param service_name Represents the name of the service that the pilot works at
     * @param user_name Represents the username of the pilot
     * @param init_license Represents the license of the pilot
     * @param init_experience Represents the experience of the pilot
     */
    void trainPilot(String service_name, String user_name, String init_license,
                    Integer init_experience) {
        if (ErrorHandle.trainPilot(service_name, user_name, init_license, init_experience)) {
            CollectionClass.getDeliveryService(service_name).trainPilot(user_name, init_license, init_experience);
        }

    }

    /**
     * Appoints a pilot.
     * @param service_name Represents the service that the pilot is appointed at
     * @param user_name Represents the username of the pilot to be appointed
     * @param drone_tag Represents the identifier of the drone the pilot flies
     */
    void appointPilot(String service_name, String user_name, Integer drone_tag) {
        if (ErrorHandle.appointPilot(service_name, user_name, drone_tag)) {
            CollectionClass.getDeliveryService(service_name).hirePilot(user_name, drone_tag);
        }
    }

    /**
     * Collects revenue.
     * @param service_name Represents the service to collect revenue from
     */
    void collectRevenue(String service_name) {
        if (ErrorHandle.collectRevenue(service_name)) {
            CollectionClass.getDeliveryService(service_name).collectRevenue();
        }
    }

    /**
     * Adds drone to swarm.
     * @param service_name Represents the service the swarm is owned by
     * @param lead_drone_tag Represents the identifier of the leading drone
     * @param swarm_drone_tag Rerpresents the identifier of the drone to join the swarm
     */
    void joinSwarm(String service_name, Integer lead_drone_tag, Integer swarm_drone_tag) {
        if (ErrorHandle.joinSwarm(service_name, lead_drone_tag, swarm_drone_tag)) {
            CollectionClass.addDroneToSwarm(service_name, lead_drone_tag, swarm_drone_tag);
        }
    }

    /**
     * Removes drone from swarm.
     * @param service_name Represents the service the swarm is owned by
     * @param swarm_drone_tag Represents the identifier of the drone to leave the swarm
     */
    void leaveSwarm(String service_name, Integer swarm_drone_tag) {
        if (ErrorHandle.leaveSwarm(service_name, swarm_drone_tag)) {

            CollectionClass.leaveSwarm(service_name, swarm_drone_tag);
        }
     }

    /**
     * Handles user input.
     */
    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";
        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("\n> " + wholeInputLine);
                if (tokens[0].indexOf("//") == 0) {
                    // deliberate empty body to recognize and skip over comments
                    // these comments ONLY work if they are at the front of the line - NOT at the middle nor end of the line
                } else if (tokens[0].equals("make_ingredient")) {
                    makeIngredient(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("display_ingredients")) {
                    displayIngredients();
                } else if (tokens[0].equals("make_location")) {
                    makeLocation(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                } else if (tokens[0].equals("display_locations")) {
                    displayLocations();
                } else if (tokens[0].equals("check_distance")) {
                    checkDistance(tokens[1], tokens[2]);
                } else if (tokens[0].equals("make_service")) {
                    makeDeliveryService(tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
                } else if (tokens[0].equals("display_services")) {
                    displayServices();
                } else if (tokens[0].equals("make_restaurant")) {
                    makeRestaurant(tokens[1], tokens[2]);
                } else if (tokens[0].equals("display_restaurants")) {
                    displayRestaurants();
                } else if (tokens[0].equals("make_drone")) {
                    makeDrone(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                } else if (tokens[0].equals("display_drones")) {
                    displayDrones(tokens[1]);
                } else if (tokens[0].equals("display_all_drones")) {
                    displayAllDrones();
                } else if (tokens[0].equals("fly_drone")) {
                    flyDrone(tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
                } else if (tokens[0].equals("load_ingredient")) {
                    loadIngredient(tokens[1], Integer.parseInt(tokens[2]), tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                } else if (tokens[0].equals("load_fuel")) {
                    loadFuel(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("purchase_ingredient")) {
                    purchaseIngredient(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], Integer.parseInt(tokens[5]));
                } else if (tokens[0].equals("make_person")) {
                    makePerson(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]);
                } else if (tokens[0].equals("display_persons")) {
                    displayPersons();
                } else if (tokens[0].equals("hire_worker")) {
                    hireWorker(tokens[1], tokens[2]);
                } else if (tokens[0].equals("fire_worker")) {
                    fireWorker(tokens[1], tokens[2]);
                } else if (tokens[0].equals("appoint_manager")) {
                    appointManager(tokens[1], tokens[2]);
                } else if (tokens[0].equals("train_pilot")) {
                    trainPilot(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]));
                } else if (tokens[0].equals("appoint_pilot")) {
                    appointPilot(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("join_swarm")) {
                    joinSwarm(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("leave_swarm")) {
                    leaveSwarm(tokens[1], Integer.parseInt(tokens[2]));
                } else if (tokens[0].equals("collect_revenue")) {
                    collectRevenue(tokens[1]);
                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;
                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }
        System.out.println("simulation terminated");
        commandLineInput.close();
    }

    /**
     * Displays a message to the user.
     * @param status Represents the generic message
     * @param text_output Represents the status of the desired operation
     */
    public void displayMessage(String status, String text_output) {
        System.out.println(status.toUpperCase() + ":" + text_output.toLowerCase());
    }
}

Georgia Institute of Technology
Georgia Institute of Technology
Georgia Institute of Technology
© 2022 GitHub, Inc.
Help
Support
API
Training
Blog
About
GitHub Enterprise Server 3.4.2
Loading complete
