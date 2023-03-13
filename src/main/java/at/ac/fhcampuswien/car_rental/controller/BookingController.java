package at.ac.fhcampuswien.car_rental.controller;


import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.UpdateBookingDTO;
import at.ac.fhcampuswien.car_rental.service.booking.BookingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@OpenAPIDefinition(info = @Info(title = "Car Endpoint", version = "1.0"))
@Log4j2
public class BookingController {
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getMyBookings() {
        return ResponseEntity.ok(bookingService.getMyBookings());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody CreateBookingDTO createBookingDTO) {
        return new ResponseEntity<>(bookingService.createBooking(createBookingDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long bookingId, @RequestBody UpdateBookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, bookingDTO));
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Void> expireBooking(@PathVariable Long bookingId) {
        bookingService.expireBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
