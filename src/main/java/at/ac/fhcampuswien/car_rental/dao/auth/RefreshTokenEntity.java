package at.ac.fhcampuswien.car_rental.dao.auth;

import at.ac.fhcampuswien.car_rental.dao.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@Table(name = "REFRESH_TOKENS")
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REFRESH_TOKEN")
    Long id;
    @Column(name = "TXT_TOKEN")
    String token;
}
