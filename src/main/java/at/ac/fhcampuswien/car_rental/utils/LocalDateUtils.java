package at.ac.fhcampuswien.car_rental.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class LocalDateUtils {

    /**
     * Checks if two time spans (startA, endA & startB, endB) overlap
     * @return true if it overlaps, false if not
     */
    public static boolean isOverlapping(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB, LocalDateTime endB) {
        return !startA.isAfter(endB) && !startB.isAfter(endA);
    }

    public static LocalDateTime convertLongToLocalDateTime(Long toConvert) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(toConvert), ZoneId.of("Europe/Paris"));
    }

    public static void validateTimespan(LocalDateTime start, LocalDateTime end) {
        LocalDateTime now = LocalDateTime.now();
        if(!start.isBefore(end) || !now.minusSeconds(1).isBefore(start)  || !now.isBefore(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Timespan is not correct!");
        }
    }
}
