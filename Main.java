/**
 * This class represents the driver class.
 * @author ipatel43
 * @author dsehgal34
 * @author skamat36
 * @author tgavaletz
 * @author jpark3068
 * @version 1.0
 */
public class Main {

    /**
     * Main method used to run the system.
     * @param args No arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Restaurant Supply Express System!");
        InterfaceLoop simulator = new InterfaceLoop();
        simulator.commandLoop();
    }
}
