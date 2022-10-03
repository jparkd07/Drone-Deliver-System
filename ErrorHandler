/**
 * This class represents the checks that occur when a method is called.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public abstract class ErrorHandle {

	/**
	 * Error check for makeIngredient.
	 * @param init_barcode Barcode of ingredient
	 * @param init_name    Name of ingredient
	 * @param init_weight  Weight of ingredient
	 * @return if checks are passed
	 */
	public static boolean makeIngredient(String init_barcode, String init_name, Integer init_weight) {
		if (CollectionClass.checkIngredientList(init_barcode)) {
			System.out.println("ERROR:ingredient_already_exists");
			return false;
		} else if (init_weight <= 0) {
			System.out.println("ERROR:weight_must_be_positive");
			return false;
		} else if (stringEmptyCheck(init_barcode) || stringEmptyCheck(init_name)) {
			return false;
		}
		return true;
	}

	/**
	 * Error check for makeDeliveryService.
	 * @param init_name    Service name
	 * @param init_revenue Service revenue
	 * @param located_at   Location
	 * @return bool if check passed
	 */
	public static boolean makeDeliveryService(String init_name, Integer init_revenue, String located_at) {
		if (stringEmptyCheck(init_name) || stringEmptyCheck(located_at)) {
			return false;
		}
		if (CollectionClass.checkDeliveryService(init_name)) {
			System.out.println("ERROR:service_already_exists");
			return false;
		}
		if (init_revenue < 0) {
			System.out.println("ERROR:revenue_must_be_positive");
			return false;
		}
		if (!CollectionClass.checkLocationList(located_at)) {
			System.out.println("ERROR:location_does_not_exist");
			return false;
		}
		return true;
	}

	/**
	 * Error check for makeLocation.
	 * @param init_name        location name
	 * @param init_x_coord     coordinate1
	 * @param init_y_coord     coordinate2
	 * @param init_space_limit space int
	 * @return if checks are passed
	 */
	public static boolean makeLocation(String init_name, Integer init_x_coord, Integer init_y_coord, Integer init_space_limit) {
		if (init_space_limit < 0) {
			System.out.println("ERROR:space_limit_must_be_positive");
			return false;
		} else if (stringEmptyCheck(init_name)) {
			return false;
		} else if (CollectionClass.checkLocationList(init_name)) {
			System.out.println("ERROR:location_already_exists");
			return false;
		} else if (CollectionClass.checkCoordinatesOccupied(init_x_coord, init_y_coord)) {
			return false;
		}
		return true;
	}

	/**
	 * makeRestaurant error check.
	 * @param init_name  restaurant name
	 * @param located_at location of restaurant
	 * @return if checks are passed
	 */
	public static boolean makeRestaurant(String init_name, String located_at) {
		if (stringEmptyCheck(init_name) || stringEmptyCheck(located_at)) {
			return false;
		} else if (CollectionClass.checkRestaurantList(init_name)) {
			System.out.println("ERROR:restaurant_already_exists");
			return false;
		}
		if (CollectionClass.checkLocationList(init_name)) {
			System.out.println("ERROR:location_occupied");
			return false;
		}
		return true;
	}

	/**
	 * Error check for makeDrone.
	 * @param service_name Represents the service the drone belongs to
	 * @param init_tag Represents the identifier for the drone
	 * @param init_capacity Represents the capacity of the drone
	 * @param init_fuel Represents the fuel of the drone
	 */
	public static boolean makeDrone(String service_name, Integer init_tag, Integer init_capacity, Integer init_fuel) {
		if (stringEmptyCheck(service_name)) {
			return false;
		} else if (!checkDelivery(service_name)) {
			return false;
		} else if (CollectionClass.checkDroneList(service_name, init_tag)) {
			System.out.println("ERROR:drone_tag_exists");
			return false;
		} else if (init_fuel < 0 || init_capacity < 0) {
			System.out.println("ERROR:integers_must_be_positive");
			return false;
		} else if (CollectionClass.remainingSpace(service_name) < 1) {
			System.out.println("ERROR:not_enough_space_to_create_new_drone");
		}
		return true;
	}

	/**
	 * Error check for makePerson.
	 * @param init_username Represents the username of the person
	 * @param init_fname Represents the first name of the person
	 * @param init_lname Represents the last name of the person
	 * @param init_year Represents the year of the person's birthday
	 * @param init_month Represents the month of the person's birthday
	 * @param init_date Represents the date of the person's birthday
	 * @param init_address Represents the address of the person
	 */
	public static boolean makePerson(String init_username, String init_fname, String init_lname,
									 Integer init_year, Integer init_month, Integer init_date, String init_address){
		if(stringEmptyCheck(init_username) || stringEmptyCheck(init_fname)|| stringEmptyCheck(init_lname)||
			stringEmptyCheck(init_address)) {
			return false;
		} else if (init_month <= 0 || init_month > 12 || init_date <= 0 || init_date > 31) {
			System.out.println("ERROR:invalid_date");
			return false;
		} else if (CollectionClass.inPersonList(init_username)) {
			System.out.println("ERROR:username_exists");
			return false;
		}
		return true;
	}

	/**
	 * Checks for if the drone can leave
	 * @param service_name    name of the delivery service
	 * @param swarm_drone_tag int of the tag of drone
	 * @return if it can leave, passing checks
	 */
	public static boolean leaveSwarm(String service_name, Integer swarm_drone_tag) {
		if (!checkDelivery(service_name)) {
			return false;
		}
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);
		if (!checkDroneExists(service_name, swarm_drone_tag)) {
			return false;
		} else if (!checkInSwarm(service_name, swarm_drone_tag)) {
			return false;
		}
		return true;
	}

	/**
	 * Error check for joinSwarm.
	 * @param service_name Represents the service the swarm is owned by
	 * @param lead_drone_tag Represents the identifier of the leading drone
	 * @param swarm_drone_tag Rerpresents the identifier of the drone to join the swarm
	 */
	public static boolean joinSwarm(String service_name, Integer lead_drone_tag, Integer swarm_drone_tag) {
		if (!checkDelivery(service_name)) {
			return false;
		}

		if (!checkDroneExists(service_name, swarm_drone_tag) || !(checkDroneExists(service_name, lead_drone_tag))) {
			return false;
		}
		if (!CollectionClass.isDroneSwarmLeader(service_name, lead_drone_tag)) {
			System.out.println("ERROR:lead_drone_not_a_leader");
			return false;
		}
		if (lead_drone_tag == swarm_drone_tag) {
			System.out.println("ERROR:duplicate_drone_tags");
			return false;
		}
		if (CollectionClass.isDroneSwarmLeader(service_name, swarm_drone_tag) && CollectionClass.getSwarm(service_name,swarm_drone_tag).getFollowers().size()>1){
			System.out.println("ERROR:swarm_drone_is_leading_a_different_swarm");
			return false;
		}
		if (!CollectionClass.getDrone(service_name, lead_drone_tag).getCurrentLoc()
				.equals(CollectionClass.getDrone(service_name, swarm_drone_tag).getCurrentLoc())) {
			System.out.println("ERROR:drones_not_at_the_same_location");
			return false;
		}

		if (CollectionClass.getSwarmList().get(service_name).get(lead_drone_tag).checkDrone(swarm_drone_tag)) {
			System.out.println("ERROR:drone_already_in_swarm");
			return false;
		}
		return true;
	}

	/**
	 * Error check for collectRevenue.
	 * @param service_name Represents the service to collect revenue from
	 */
	public static boolean collectRevenue(String service_name) {
		if (!checkDelivery(service_name)) {
			return false;
		}
		if (!CollectionClass.getDeliveryService(service_name).hasManager()) {
			System.out.println("ERROR:delivery_service_does_not_have_manager");
			return false;
		}
		return true;
	}

	public static boolean appointPilot(String service_name, String user_name, Integer drone_tag) {
		if (!checkDelivery(service_name)) {
			return false;
		}
		if (CollectionClass.getPerson(user_name) == null) {
			System.out.println("ERROR:person_does_not_exist");
			return false;
		}
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);
		if (!checkDroneExists(service_name, drone_tag)) {
			return false;
		}
		Person p = CollectionClass.getPerson(user_name);
		if (!ds.doTheyWorkHere(user_name)) {
			System.out.println("ERROR:does_not_work_here");
			return false;
		}
		if (CollectionClass.isManaging(user_name)) {
			System.out.println("ERROR:employee_too_busy_managing");
			return false;
		}
		if (p.getPilotID() == null) {
			System.out.println("ERROR:must_have_valid_pilot_license");
			return false;
		}else if (!CollectionClass.isEmployee(user_name)) {
			System.out.println("ERROR:needs_to_be_an_employee");
			return false;
		}

		if (CollectionClass.isPilot(user_name) && ds.getPilot(user_name).getEmployedAt().equals(null)) {
			System.out.println("ERROR:cannot_be_working_two_delivery_services_as_pilot");
			return false;
		}
		return true;
	}

	public static boolean trainPilot(String service_name, String user_name, String init_license, Integer init_experience) {
		if (!checkDelivery(service_name)) {
			return false;
		}
		if (CollectionClass.getPerson(user_name) == null) {
			System.out.println("ERROR:person_does_not_exist");
			return false;
		}
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);
		Person p = CollectionClass.getPerson(user_name);
		if (CollectionClass.isManaging(user_name)) {
			System.out.println("ERROR:employee_is_too_busy_managing");
			return false;
		} else if (!CollectionClass.isEmployee(user_name)) {
			System.out.println("ERROR:needs_to_be_an_employee");
			return false;
		} else if (!ds.getEmployee(user_name).singleEmployment()) {
			System.out.println("ERROR:cannot_be_working_two_delivery_services_as_pilot");
			return false;
		} else if (!(ds.areTheyAnEmployee(user_name))) {
			System.out.println("ERROR:must_work_at_delivery_service");
			return false;
		} else if (!CollectionClass.getDeliveryService(service_name).hasManager()) {
			System.out.println("ERROR:service_must_have_manager");
			return false;
		} else if (init_experience < 0) {
			System.out.println("ERROR:experience_cannot_be_negative");
			return false;
		}
		return true;
	}

	/**
	 * Error check for appointManager.
	 * @param service_name Represents the service that appoints a manager
	 * @param user_name Represents the username of the person to be appointed
	 */
	public static boolean appointManager(String service_name, String user_name) {
		if (!checkDelivery(service_name)) {
			return false;
		}
		if (CollectionClass.getPerson(user_name) == null) {
			System.out.println("ERROR:person_does_not_exist");
			return false;
		}
		Person p = CollectionClass.getPerson(user_name);
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);
		if (CollectionClass.isManaging(user_name)) {
			System.out.println("ERROR:already_managing_other_delivery_service");
			return false;
		}
		if (CollectionClass.isPilot(user_name)) {
			System.out.println("ERROR:currently_flying_drones");
			return false;
		}
		if (!CollectionClass.isEmployee(user_name)) {
			System.out.println("ERROR:not_employed");
			return false;
		}
		if (!(ds.doTheyWorkHere(user_name))) {
			System.out.println("ERROR:employee_does_not_work_for_this_service");
			return false;
		}
		if (!ds.getEmployee(user_name).singleEmployment()) {
			System.out.println("ERROR:employee_is_working_at_other_companies");
			return false;
		}
		return true;
	}

	/**
	 * Error check for fireWorker.
	 * @param service_name Represents the service that fires the worker
	 * @param user_name Represents the username of the person to be fired
	 */
	public static boolean fireWorker(String service_name, String user_name) {
		if (CollectionClass.getPerson(user_name) == null) {
			System.out.println("ERROR:person_does_not_exist");
			return false;
		}
		if (!checkDelivery(service_name)) {
			return false;
		}
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);
		Person p = CollectionClass.getPerson(user_name);
		if (!(ds.doTheyWorkHere(user_name))) {
			System.out.println("ERROR:worker_does_not_work_at_delivery_service");
			return false;
		}
		if (ds.getManagedBy() != null) {

			if (ds.getManagedBy().getUserName().equals(user_name)) {
				System.out.println("ERROR:employee_is_managing_a_service");
				return false;
			}
		}
		if (ds.isPilot(user_name)) {

			if (ds.getPilot(user_name).currentlyPiloting()) {
				System.out.println("ERROR:currently_piloting_drones");
				return false;
			}
		}
		return true;
	}

	/**
	 * Error check for hire worker.
	 * @param service_name Represents the service that hires the worker
	 * @param user_name Represents the username of the person to be hired
	 */
	public static boolean hireWorker(String service_name, String user_name) {
		if (CollectionClass.getPerson(user_name) == null) {
			System.out.println("ERROR:person_does_not_exist");
			return false;
		}
		if (!checkDelivery(service_name)) {
			return false;
		}
		Person p = CollectionClass.getPerson(user_name);
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);
		if (ds.doTheyWorkHere(user_name)) {
			System.out.println("ERROR:worker_already_works_here");
			return false;
		} else if (CollectionClass.isManaging(user_name)) {
			System.out.println("ERROR:already_employed_as_manager_at_different_company");
			return false;
		} else if (CollectionClass.isPilot(user_name)) {
			System.out.println("ERROR:already_employed_as_pilot_at_different_company");
			return false;
		}
		return true;
	}

	/**
	 * Purchases an ingredient.
	 * @param restaurant_name Represents the restaurant that purchases the ingredient
	 * @param service_name Represents the service that the drone belongs to
	 * @param drone_tag Represents the identifier for the drone
	 * @param barcode Represents the barcode for the ingredient
	 * @param quantity Represents the quantity of the ingredient
	 */
	public static boolean purchaseIngredient(String restaurant_name, String service_name, Integer drone_tag, String barcode, Integer quantity) {
		if (!checkDelivery(service_name)) {
			return false;
		}
		if (quantity < 0) {
			System.out.println("ERROR:quantity_must_be_positive");
			return false;
		}

		if (!checkDroneExists(service_name, drone_tag)) {
			return false;
		}
		if (!checkIngredient(barcode)) {
			return false;
		}
		if (!(CollectionClass.checkRestaurantList(restaurant_name))) {
			System.out.println("ERROR:restaurant_not_found");
			return false;
		}

		if (!(CollectionClass.getDrone(service_name, drone_tag).getPayload().containsKey(barcode))) {
			System.out.println("ERROR:drone_does_not_have_ingredient");
			return false;
		}
		if (!(CollectionClass.getDrone(service_name, drone_tag).getCurrentLoc().equals(CollectionClass.getRestaurant(restaurant_name).getLoc()))) {
			System.out.println("ERROR:drone_not_located_at_restaurant");
			return false;
		} else {
			int inventory = CollectionClass.getDrone(service_name, drone_tag).getPayload(barcode).getQuantity();
			if (inventory < quantity) {
				System.out.println("ERROR:drone_does_not_have_enough_ingredient");
				return false;
			}
		}
		return true;
	}

	/**
	 * Error check for loadFuel.
	 * @param service_name Represents the service that the drone belongs to
	 * @param drone_tag Represents the identifier for the drone
	 * @param petrol Represents how much fuel to add
	 */
	public static boolean loadFuel(String service_name, Integer drone_tag, Integer petrol) {
		if (!checkDelivery(service_name)) {
			return false;
		} else if (!checkDroneExists(service_name, drone_tag)) {
			return false;
		}
		if (petrol < 0) {
			System.out.println("ERROR:petrol_cannot_be_negative");
			return false;
		}
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);

		if (!(CollectionClass.getDrone(service_name, drone_tag).getCurrentLoc().equals(ds.getLoc()))) {
			System.out.println("ERROR:drone_not_located_at_home_base");
			return false;
		}
		if (!(ds.hasEmployee())) {
			System.out.println("ERROR:delivery_service_has_no_employees");
			return false;
		}
		return true;
	}

	/**
	 * Error check for loadIngredient.
	 * @param service_name Represents the service that the drone belongs to
	 * @param drone_tag Represents the identifier for the drone
	 * @param barcode Represents the barcode for the ingredient
	 * @param quantity Represents the quantity of the ingredient
	 * @param unit_price Represents the unit price of the ingredient
	 */
	public static boolean loadIngredient(String service_name, Integer drone_tag, String barcode, Integer quantity, Integer unit_price) {
		if (!checkDelivery(service_name)) {
			return false;
		}
		if (!checkIngredient(barcode)) {
			return false;
		}
		if (unit_price < 0) {
			System.out.println("ERROR:price_cannot_be_negative");
			return false;
		}
		if (quantity < 0) {
			System.out.println("ERROR:quantity_cannot_be_negative");
			return false;
		}
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);
		if (!checkDroneExists(service_name, drone_tag)) {
			return false;
		}
		if (!(CollectionClass.getDrone(service_name, drone_tag).getCurrentLoc().equals(ds.getLoc()))) {
			System.out.println("ERROR:drone_not_located_at_home_base");
			return false;
		}
		if ((CollectionClass.getDrone(service_name, drone_tag).getCurrCapacityLeft() < quantity)) {
			System.out.println("ERROR:not_enough_capacity");
			return false;
		}
		if (!ds.hasWarehouseEmployee()) {
			System.out.println("ERROR:delivery_service_has_no_workers");
			return false;
		}
		return true;
	}

	/**
	 * Checks if a swarm can fly to a location.
	 * @param destination Represents the location to fly to
	 * @param service Represents the service the swarm is owned by
	 * @param swarm Represents the swarm to be evaluated
	 * @return Returns a boolean value determining if the swarm can fly to a location
	 */
	public static boolean checkSwarmFlight(Location destination, DeliveryService service, Swarm swarm) {
		if (!checkDelivery(service.getName())) {
			return false;
		}
		if (!checkLocation(destination.getName())) {
			return false;
		}
		if (swarm.getLeader().getFuel() < swarm.getLeader().getCurrentLoc().calcDistance(destination) + destination.calcDistance(service.getLoc())) {
			System.out.println("ERROR:a_drone_in_swarm_does_not_have_enough_fuel_to_complete_trip");
			return false;
		} /*else if (swarm.getLeader().getFuel() < swarm.getLeader().getCurrentLoc().calcDistance(service.getLoc())) {
            System.out.println("ERROR:a_drone_in_swarm_does_not_have_enough_fuel_to_reach_home_base_from_the_destination");
            return false;
        }*/
		if (swarm.getFollowers().size() + 1 > destination.getRemaining()) {
			System.out.println("ERROR:not_enough_space_to_maneuver_the_swarm_to_that_location");
			return false;
		} else {
			for (Drone single : swarm.getFollowers()) {
				if (single.getFuel() < single.getCurrentLoc().calcDistance(destination) + destination.calcDistance(service.getLoc())) {
					System.out.println("ERROR:a_drone_in_swarm_does_not_have_enough_fuel_to_complete_trip");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Error check for flyDrone.
	 * @param service_name Represents the service the drone belongs to
	 * @param drone_tag Represents the identifier for the drone
	 * @param destination_name Represents the destination for the drone
	 */
	public static boolean flyDrone(String service_name, Integer drone_tag, String destination_name) {
		if (!checkDelivery(service_name)) {
			return false;
		}
		if (!checkLocation(destination_name)) {
			return false;
		}
		DeliveryService ds = CollectionClass.getDeliveryService(service_name);
		if (!checkDroneExists(service_name, drone_tag)) {
			return false;
		}
		if (!CollectionClass.isDroneSwarmLeader(service_name, drone_tag) && !CollectionClass.getUnassignedDrones(service_name).containsKey(drone_tag)) {
			System.out.println("ERROR:drone_is_not_leader_of_swarm");
			return false;
		}//the next condition should never be reached.
        /*else if (!CollectionClass.isDroneInSwarm(service_name, drone_tag)) {
            System.out.println("ERROR:drone_not_assigned_to_a_pilot");
            return false;
        }*/
		return true;
	}

	/**
	 * Checks if a drone can fly to a location
	 * @param service_name Represents the service the drone belongs to
	 * @param drone_tag Represents the identifier for the drone
	 * @param destination_name Represents the destination of the drone
	 * @return
	 */
	public boolean checkFlight(String service_name, Integer drone_tag, String destination_name) {
		if (!checkDelivery(service_name)) {
			return false;
		} else if (!checkDroneExists(service_name, drone_tag)) {
			return false;
		} else if (!checkLocation(destination_name)) {
			return false;
		}
		Drone d = CollectionClass.getDrone(service_name, drone_tag);
		if (d.getFuel() < d.getCurrentLoc().calcDistance(CollectionClass.getLocation(destination_name))) {
			System.out.println("ERROR:not_enough_fuel_to_reach_the_destination");
			return false;
		} else if (d.getFuel() < d.getCurrentLoc().calcDistance(CollectionClass.getLocation(destination_name))
				+ CollectionClass.getDeliveryService(service_name).getLoc().calcDistance(CollectionClass.getLocation(destination_name))) {
			System.out.println("ERROR:not_enough_fuel_to_reach_home_base_from_the_destination");
			return false;

		} else if (!(CollectionClass.getLocation(destination_name).checkSpace())) {
			System.out.println("ERROR:not_enough_space_at_destination");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if a delivery service exists.
	 * @param service_name Represents the service to be checked
	 * @return Returns a boolean value indicating if the service exists
	 */
	private static boolean checkDelivery(String service_name) {
		if (!CollectionClass.checkDeliveryService(service_name)) {
			System.out.println("ERROR:delivery_service_does_not_exist");
			return false;
		}
		return true;
	}

	/**
	 * Checks if an ingredient exists.
	 * @param barcode Represents the ingredient to be checked
	 * @return Returns a boolean value indicating if the ingredient exists
	 */
	private static boolean checkIngredient(String barcode) {
		if (!CollectionClass.checkIngredientList(barcode)) {
			System.out.println("ERROR:ingredient_not_found");
			return false;
		}
		return true;
	}

	/**
	 * Checks if a location exists.
	 * @param name Represents the location to be checked
	 * @return Returns a boolean value indicating if the location exists
	 */
	private static boolean checkLocation(String name) {
		if (!CollectionClass.checkLocationList(name)) {
			System.out.println("ERROR:location_not_found");
			return false;
		}
		return true;
	}

	/**
	 * Checks if a string is empty.
	 * @param s Represents the string to be checked
	 * @return Returns a boolean indicating if the string is empty
	 */
	private static boolean stringEmptyCheck(String s) {
		if (s == null || s.isEmpty()) {
			System.out.println("ERROR:empty_string_unacceptable");
			return true;
		}
		return false;
	}

	/**
	 * Checks if a drone exists at a service.
	 * @param service_name Represents the service the drone belongs to
	 * @param swarm_drone_tag Represents the identifier of the drone
	 * @return Returns a boolean value indicating if the drone exists
	 */
	private static boolean checkDroneExists(String service_name, Integer swarm_drone_tag) {
		if (!CollectionClass.checkDroneList(service_name, swarm_drone_tag)) {
			System.out.println("ERROR:drone_not_in_service");
			return false;
		}
		return true;
	}

	/**
	 * Checks if a drone is in a swarm.
	 * @param service_name Represents the service the swarm belongs to
	 * @param swarm_drone_tag Represents the identifier for the swarm
	 * @return Returns a boolean value indicating if a drone is in the swarm.
	 */
	private static boolean checkInSwarm(String service_name, int swarm_drone_tag) {
		if (!CollectionClass.isDroneInSwarm(service_name, swarm_drone_tag)) {
			System.out.println("ERROR:drone_not_in_swarm");
			return false;
		}
		return true;

	}

	/**
	 * Checks if a drone is the leader of a swarm.
	 * @param service_name Represents the service the swarm belongs to
	 * @param swarm_drone_tag Represents the identifier for the swarm
	 * @return Returns a boolean value indicating if a drone is the leader of a swarm
	 */
	public static boolean isDroneLeaderLeader(String service_name, Integer swarm_drone_tag) {
		if (!CollectionClass.isDroneSwarmLeader(service_name, swarm_drone_tag)) {
			System.out.println("ERROR:drone_is_not_leader_of_swarm");
			return false;
		}
		return true;
	}
}
