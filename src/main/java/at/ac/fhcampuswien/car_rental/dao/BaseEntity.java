package at.ac.fhcampuswien.car_rental.dao;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity implements Serializable {
    @Column(name = "VERSION")
    @Version
    Integer version;
    @Column(name = "TST_CREATED_ON")
    Instant createdOn;
    @Column(name = "TST_UPDATED_ON")
    Instant updatedOn;
}
