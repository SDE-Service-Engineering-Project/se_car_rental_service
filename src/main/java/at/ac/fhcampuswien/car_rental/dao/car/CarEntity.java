package at.ac.fhcampuswien.car_rental.dao.car;

import at.ac.fhcampuswien.car_rental.dao.BaseEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CARS")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarEntity extends BaseEntity {
    @Id
    @Column(name = "ID_CAR")
    Long carId;
    @Length(min = 3, max = 32, message = "Brand must be {min} and {max} characters long")
    @Column(name = "TXT_BRAND")
    String brand;
    @Length(min = 2, max = 32, message = "Model must be {min} and {max} characters long")
    @Column(name = "TXT_MODEL")
    String model;
    @Column(name = "NUM_CONSTRUCTION_YEAR")
    Integer constructionYear;
    @Column(name = "BI_PRICE")
    Float price;
    @Column(name = "TXT_CURRENCY")
    String currency;
    @OneToMany(mappedBy="car")
    Set<BookingEntity> bookings;
}
