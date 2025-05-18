import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger class implementing the Singleton pattern to provide
 * centralized logging capability throughout the application
 */
public class Logger {
    // Singleton instance
    private static Logger instance;

    // Log level constants (higher number means more verbose)
    private static final int ERROR_LEVEL = 1;
    private static final int DEBUG_LEVEL = 2;
    private static final int INFORM_LEVEL = 3;

    // Current log level setting
    private int logLevel;

    // Date format for timestamps
    private SimpleDateFormat dateFormat;

    /**
     * Private constructor to prevent instantiation from outside
     * This is essential for the Singleton pattern
     */
    private Logger() {
        // Default to most verbose logging
        this.logLevel = INFORM_LEVEL;
        // Initialize date formatter
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    /**
     * Get the singleton instance of Logger
     * Creates the instance if it doesn't exist (lazy initialization)
     *
     * @return The Logger instance
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Set the current logging level
     *
     * @param level The log level to set (1=ERROR, 2=DEBUG, 3=INFORM)
     */
    public void setLogLevel(int level) {
        if (level >= ERROR_LEVEL && level <= INFORM_LEVEL) {
            this.logLevel = level;

            switch(level) {
                case ERROR_LEVEL:
                    logMessage("SYSTEM", "Logging level set to ERROR only");
                    break;
                case DEBUG_LEVEL:
                    logMessage("SYSTEM", "Logging level set to ERROR and DEBUG");
                    break;
                case INFORM_LEVEL:
                    logMessage("SYSTEM", "Logging level set to all messages");
                    break;
            }
        } else {
            logMessage("ERROR", "Invalid log level specified: " + level);
        }
    }

    /**
     * Log an error message
     *
     * @param message The message to log
     */
    public void error(String message) {
        if (isLevelEnabled(ERROR_LEVEL)) {
            logMessage("ERROR", message);
        }
    }

    /**
     * Log a debug message
     *
     * @param message The message to log
     */
    public void debug(String message) {
        if (isLevelEnabled(DEBUG_LEVEL)) {
            logMessage("DEBUG", message);
        }
    }

    /**
     * Log an informational message
     *
     * @param message The message to log
     */
    public void inform(String message) {
        if (isLevelEnabled(INFORM_LEVEL)) {
            logMessage("INFORM", message);
        }
    }

    /**
     * Check if a message level should be logged based on current settings
     *
     * @param messageLevel The level to check
     * @return true if the level is enabled, false otherwise
     */
    private boolean isLevelEnabled(int messageLevel) {
        return messageLevel <= this.logLevel;
    }

    /**
     * Write a formatted message to the log with level and timestamp
     *
     * @param level The log level
     * @param message The message content
     */
    private void logMessage(String level, String message) {
        String timestamp = getCurrentTimestamp();
        String formattedMessage = level + " - " + timestamp + " - " + message;

        // In a real implementation, this would write to a file
        // For this example, we just print to console
        System.out.println(formattedMessage);
    }

    /**
     * Get the current timestamp formatted as required
     *
     * @return Formatted timestamp string
     */
    public String getCurrentTimestamp() {
        return dateFormat.format(new Date());
    }
}