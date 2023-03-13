package at.ac.fhcampuswien.car_rental.dao.auth;

import at.ac.fhcampuswien.car_rental.dao.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    Long userId;
    @Length(min = 3, max = 32, message = "Username must be {min} and {max} characters long")
    @Column(name = "TXT_USERNAME", unique = true)
    String userName;
    @Length(min = 1, max = 255, message = "Password must be {min} and {max} characters long")
    @Column(name = "HASH_PASSWORD")
    String password;
    @Length(min = 3, max = 30, message = "First name must be {min} and {max} characters long")
    @Column(name = "TXT_FIRST_NAME")
    String firstName;
    @Length(min = 3, max = 30, message = "Last name must be {min} and {max} characters long")
    @Column(name = "TXT_LAST_NAME")
    String lastName;
}
