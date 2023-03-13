-- -----------------------------------------------------
-- Schema car_rental
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table Cars
-- -----------------------------------------------------
DROP TABLE IF EXISTS refresh_tokens;
DROP TABLE IF EXISTS bookings ;
DROP TABLE IF EXISTS users ;
DROP TABLE IF EXISTS cars ;

CREATE TABLE cars (
    ID_CAR INT NOT NULL AUTO_INCREMENT,
    VERSION INT NOT NULL,
    TST_CREATED_ON DATETIME NOT NULL,
    TST_UPDATED_ON DATETIME,
    TXT_BRAND VARCHAR(45) NOT NULL,
    TXT_MODEL VARCHAR(128) NOT NULL,
    NUM_CONSTRUCTION_YEAR INT NOT NULL,
    FLOAT_PRICE FLOAT NOT NULL,
    INT_PRECISION VARCHAR(45) NOT NULL,
    TXT_CURRENCY VARCHAR(8) NOT NULL,
    PRIMARY KEY (ID_CAR))
;


-- -----------------------------------------------------
-- Table Users
-- -----------------------------------------------------


CREATE TABLE users (
    ID_USER INT NOT NULL AUTO_INCREMENT,
    VERSION INT NOT NULL,
    TST_CREATED_ON DATETIME NOT NULL,
    TST_UPDATED_ON DATETIME,
    TXT_USERNAME VARCHAR(48) NOT NULL UNIQUE,
    HASH_PASSWORD VARCHAR(512) NOT NULL,
    TXT_FIRST_NAME VARCHAR(48) NOT NULL,
    TXT_LAST_NAME VARCHAR(48) NOT NULL,
    PRIMARY KEY (ID_USER))
;

-- -----------------------------------------------------
-- Table Refresh Token
-- -----------------------------------------------------


CREATE TABLE refresh_tokens (
    ID_REFRESH_TOKEN INT NOT NULL AUTO_INCREMENT,
    VERSION INT NOT NULL,
    TST_CREATED_ON DATETIME NOT NULL,
    TST_UPDATED_ON DATETIME,
    TXT_TOKEN VARCHAR(256) NOT NULL,
    PRIMARY KEY (ID_REFRESH_TOKEN))
;



-- -----------------------------------------------------
-- Table Bookings
-- -----------------------------------------------------


CREATE TABLE bookings (
    ID_BOOKING INT NOT NULL AUTO_INCREMENT,
    VERSION INT NOT NULL,
    TST_CREATED_ON DATETIME NOT NULL,
    TST_UPDATED_ON VARCHAR(45),
    TST_BOOKED_UNTIL DATETIME NOT NULL,
    TXT_BOOKING_STATUS VARCHAR(24) NOT NULL,
    FLOAT_BOOKING_PRICE FLOAT NOT NULL,
    INT_PRECISION INT NOT NULL,
    ID_CAR INT NOT NULL,
    ID_USER INT NOT NULL,
    PRIMARY KEY (ID_BOOKING),
    CONSTRAINT fk_bookings_cars
    FOREIGN KEY (ID_CAR)
    REFERENCES cars (ID_CAR),
    CONSTRAINT fk_bookings_users
    FOREIGN KEY (ID_USER)
    REFERENCES users (ID_USER))
;

CREATE INDEX fk_bookings_cars_idx ON bookings (ID_CAR);

CREATE INDEX fk_bookings_users_idx ON bookings (ID_USER);

COMMIT;
