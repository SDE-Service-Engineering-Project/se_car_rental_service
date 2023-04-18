package at.ac.fhcampuswien.car_rental.dao;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity implements Serializable {
    @Column(name = "VERSION")
    @Version
    Integer version;
    @Column(name = "TST_CREATED_ON")
    LocalDateTime createdOn;
}
