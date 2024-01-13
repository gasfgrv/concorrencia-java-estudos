package executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest01 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledThreadPoolTest01.class);
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void beeper() {
        Runnable r = () -> LOGGER.info(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " beep");
//        SCHEDULED_EXECUTOR_SERVICE.schedule(r, 5, TimeUnit.SECONDS);

        ScheduledFuture<?> scheduledFuture = SCHEDULED_EXECUTOR_SERVICE.scheduleWithFixedDelay(r, 1, 5, TimeUnit.SECONDS);
        SCHEDULED_EXECUTOR_SERVICE.schedule(() -> {
            LOGGER.info("Cancelando o scheduleWithFixedDelay");
            scheduledFuture.cancel(false);
            SCHEDULED_EXECUTOR_SERVICE.shutdown();
        }, 10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        String formatted = LocalTime.now().format(FORMATTER);
        LOGGER.info(formatted);
        beeper();
    }
}
