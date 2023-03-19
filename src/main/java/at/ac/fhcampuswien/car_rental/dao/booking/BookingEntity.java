package at.ac.fhcampuswien.car_rental.dao.booking;

import at.ac.fhcampuswien.car_rental.dao.BaseEntity;
import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOKINGS")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOOKING")
    Long bookingId;
    @Column(name = "TST_BOOKED_UNTIL")
    LocalDateTime bookedUntil;
    @Column(name = "TXT_BOOKING_STATUS")
    @Enumerated(EnumType.STRING)
    BookingStatus bookingStatus;
    @Column(name = "BI_BOOKING_PRICE")
    Float price;
    @Column(name = "TXT_CURRENCY")
    String currency;
    @Column(name = "ID_CAR")
    Long carId;
    @Column(name = "ID_USER")
    Long userId;
}
