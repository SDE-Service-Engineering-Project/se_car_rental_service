package at.ac.fhcampuswien.car_rental.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static java.time.temporal.ChronoUnit.DAYS;

@UtilityClass
public class LocalDateUtils {

    /**
     * Checks if two time spans (startA, endA & startB, endB) overlap
     * @return true if it overlaps, false if not
     */
    public boolean isOverlapping(LocalDate startA, LocalDate endA, LocalDate startB, LocalDate endB) {
        return !startA.isAfter(endB) && !startB.isAfter(endA);
    }

    public LocalDate convertLongToLocalDate(Long toConvert) {
        return Instant.ofEpochMilli(toConvert).atZone(ZoneId.of("Europe/Vienna")).toLocalDate();
    }

    public void validateTimespan(LocalDate start, LocalDate end) {
        LocalDate now = LocalDate.now();
        if(!isBeforeOrEqual(end, start) || !isBeforeOrEqual(start, now)  || !isBeforeOrEqual(end, now)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Timespan is not correct!");
        }
    }

    public boolean isBeforeOrEqual(LocalDate date, LocalDate compareToDate) {
        if (date == null || compareToDate == null) {
            return false;
        }
        return compareToDate.isBefore(date) || compareToDate.isEqual(date);
    }

    public long calculateDaysBetween(LocalDate start, LocalDate end) {
        return DAYS.between(start, end) + 1;
    }
}
