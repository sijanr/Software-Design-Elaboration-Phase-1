package technicalservices.logger;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

public class ConsoleLogger implements Logger{

    @Override
    public void log(String message) {
        String dateTime = getDate();
        System.out.println(dateTime + "   | " + message.trim());
    }

    private String getDate() {
        Date date = Date.from(Instant.now());
        return DateFormat.getDateTimeInstance().format(date);
    }

}
