import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Create a logger
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.log(Level.INFO, "Hello, world!");
    }
}
