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
    ID_CAR bigserial NOT NULL,
    VERSION INTEGER NOT NULL,
    TST_CREATED_ON timestamp NOT NULL,
    TXT_BRAND VARCHAR(45) NOT NULL,
    TXT_MODEL VARCHAR(128) NOT NULL,
    NUM_CONSTRUCTION_YEAR INTEGER NOT NULL,
    BI_PRICE NUMERIC NOT NULL,
    TXT_CURRENCY VARCHAR(8) NOT NULL,
    PRIMARY KEY (ID_CAR))
;


-- -----------------------------------------------------
-- Table Users
-- -----------------------------------------------------


CREATE TABLE users (
    ID_USER bigserial NOT NULL,
    VERSION INTEGER NOT NULL,
    TST_CREATED_ON timestamp NOT NULL,
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
    ID_REFRESH_TOKEN bigserial NOT NULL,
    VERSION INTEGER NOT NULL,
    TST_CREATED_ON timestamp NOT NULL,
    TXT_TOKEN VARCHAR(256) NOT NULL,
    PRIMARY KEY (ID_REFRESH_TOKEN))
;


-- -----------------------------------------------------
-- Table Bookings
-- -----------------------------------------------------


CREATE TABLE bookings (
    ID_BOOKING bigserial NOT NULL,
    VERSION INTEGER NOT NULL,
    TST_CREATED_ON timestamp NOT NULL,
    TST_BOOKED_FROM DATE DEFAULT CURRENT_DATE,
    TST_BOOKED_UNTIL DATE NOT NULL,
    TXT_BOOKING_STATUS VARCHAR(24) NOT NULL,
    BI_BOOKING_PRICE NUMERIC NOT NULL,
    TXT_CURRENCY VARCHAR(8) NOT NULL,
    BI_BOOKING_PRICE_SAVED NUMERIC, -- Not the original price
    TXT_CURRENCY_SAVED VARCHAR(8), -- Not the original currency - the currency set by the user
    ID_CAR INTEGER NOT NULL,
    ID_USER INTEGER NOT NULL,
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
