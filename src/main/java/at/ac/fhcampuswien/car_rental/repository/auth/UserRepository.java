package at.ac.fhcampuswien.car_rental.repository.auth;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);

    void deleteByUserName(String userName);
}
