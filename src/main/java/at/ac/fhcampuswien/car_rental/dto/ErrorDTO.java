package at.ac.fhcampuswien.car_rental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record ErrorDTO(String field,
                       String message,
                       @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ",timezone = "UTC")
                       Instant timestamp,
                       String path) {
}
