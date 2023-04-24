package at.ac.fhcampuswien.car_rental.dao.booking;

import at.ac.fhcampuswien.car_rental.dao.BaseEntity;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    @Column(name = "TST_BOOKED_FROM")
    LocalDate bookedFrom;
    @Column(name = "TST_BOOKED_UNTIL")
    LocalDate bookedUntil;
    @Column(name = "TXT_BOOKING_STATUS")
    @Enumerated(EnumType.STRING)
    BookingStatus bookingStatus;
    @Column(name = "BI_BOOKING_PRICE")
    BigDecimal price;
    @Column(name = "TXT_CURRENCY")
    String currency;
    @Column(name = "BI_BOOKING_PRICE_SAVED")
    BigDecimal priceSaved;
    @Column(name = "TXT_CURRENCY_SAVED")
    String currencySaved;
    @Column(name = "ID_CAR")
    Long carId;
    @ManyToOne
    @JoinColumn(name = "ID_CAR", nullable = false, insertable = false, updatable = false)
    private CarEntity car;

    @Column(name = "ID_USER")
    Long userId;

    public BookingEntity setStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
        return this;
    }
}
