package at.ac.fhcampuswien.car_rental.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateUtils {

    /**
     * Checks if two time spans (startA, endA & startB, endB) overlap
     * @return true if it overlaps, false if not
     */
    public static boolean isOverlapping(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB, LocalDateTime endB) {
        return !startA.isAfter(endB) && !startB.isAfter(endA);
    }

    public static LocalDateTime convertLongToLocalDateTime(Long toConvert) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(toConvert), ZoneId.systemDefault());
    }
}
