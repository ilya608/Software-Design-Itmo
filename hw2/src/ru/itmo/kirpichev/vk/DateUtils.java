package ru.itmo.kirpichev.vk;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author ilyakirpichev
 */
public class DateUtils {
    public static Long minusHoursFromNow(int hours) {
        LocalDateTime res = LocalDateTime.now().minusHours(hours);
        return Timestamp.valueOf(res).getTime() / 1000L;
    }

    public static LocalDateTime detLocalDateTimeFromTimestamp(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }
}