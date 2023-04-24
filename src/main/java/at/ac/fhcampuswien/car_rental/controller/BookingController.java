package at.ac.fhcampuswien.car_rental.controller;


import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingResponseDTO;
import at.ac.fhcampuswien.car_rental.service.booking.BookingService;
import at.ac.fhcampuswien.car_rental.utils.LocalDateUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Log4j2
public class BookingController {
    BookingService bookingService;
    @Operation(summary = "Get all Bookings associated with the user")
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getMyBookings() {
        return ResponseEntity.ok(bookingService.getMyBookings());
    }

    @Operation(summary = "Get a Booking by Id")
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @Operation(summary = "Create a Booking")
    @PostMapping
    public ResponseEntity<CreateBookingResponseDTO> createBooking(@Valid @RequestBody CreateBookingDTO createBookingDTO) {
        LocalDateUtils.validateTimespan(Objects.requireNonNullElse(createBookingDTO.bookedFrom(), LocalDate.now()), createBookingDTO.bookedUntil());
        return new ResponseEntity<>(bookingService.createBooking(createBookingDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Expire a Booking")
    @PatchMapping("/{bookingId}")
    public ResponseEntity<Void> expireBooking(@PathVariable Long bookingId) {
        bookingService.expireBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
