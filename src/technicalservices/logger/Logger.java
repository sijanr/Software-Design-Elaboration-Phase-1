package technicalservices.logger;

public interface Logger {
    static Logger createLogger() {
        return new ConsoleLogger();
    }
    void log(String message);
}
