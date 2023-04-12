package at.ac.fhcampuswien.car_rental.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.fail;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocalDateUtilsTest {

    @Test
    public void should_return_true_because_overlapping() {
        LocalDateTime startA = LocalDateTime.now().minusDays(5);
        LocalDateTime endA = LocalDateTime.now().minusDays(2);
        LocalDateTime startB = LocalDateTime.now().minusDays(4);
        LocalDateTime endB = LocalDateTime.now().minusDays(1);

        boolean result = LocalDateUtils.isOverlapping(startA, endA, startB, endB);

        Assertions.assertTrue(result);
    }

    @Test
    public void should_return_false_because_not_overlapping() {
        LocalDateTime startA = LocalDateTime.now().minusDays(5);
        LocalDateTime endA = LocalDateTime.now().minusDays(3);
        LocalDateTime startB = LocalDateTime.now().minusDays(2);
        LocalDateTime endB = LocalDateTime.now().minusDays(1);

        boolean result = LocalDateUtils.isOverlapping(startA, endA, startB, endB);

        Assertions.assertFalse(result);
    }

    @Test
    public void should_convert_long_to_datetime() {
        Long toConvert = 1680559200000L;
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(
                LocalDate.of(2023, 4, 4),
                LocalTime.of(0, 0, 0, 0)
        );

        LocalDateTime result = LocalDateUtils.convertLongToLocalDateTime(toConvert);

        Assertions.assertEquals(expectedLocalDateTime, result);
    }

    @Test
    public void should_not_throw_error_to_valid_timestamp() {
        LocalDateTime start = LocalDateTime.now().minusDays(5);
        LocalDateTime end = LocalDateTime.now().minusDays(3);

        try {
            LocalDateUtils.validateTimespan(start, end);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void should_throw_error_to_invalid_timestamp() {
        LocalDateTime start = LocalDateTime.now().minusDays(5);
        LocalDateTime end = LocalDateTime.now().minusDays(6);


        Assertions.assertThrows(
                ResponseStatusException.class, () -> {
                    LocalDateUtils.validateTimespan(start, end);
                }
        );
    }
}
