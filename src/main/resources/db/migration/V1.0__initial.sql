-- -----------------------------------------------------
-- Schema car_rental
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table Cars
-- -----------------------------------------------------
DROP TABLE IF EXISTS Bookings ;
DROP TABLE IF EXISTS Users ;
DROP TABLE IF EXISTS Cars ;

CREATE TABLE Cars (
    ID_CAR INT NOT NULL,
    VERSION INT NOT NULL,
    TST_CREATED_ON DATETIME NOT NULL,
    TST_UPDATED_ON DATETIME NOT NULL,
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


CREATE TABLE Users (
    ID_USER INT NOT NULL,
    VERSION INT NOT NULL,
    TST_CREATED_ON DATETIME NOT NULL,
    TST_UPDATED_ON DATETIME NOT NULL,
    TXT_USERNAME VARCHAR(48) NOT NULL,
    HASH_PASSWORD VARCHAR(512) NOT NULL,
    TXT_FIRST_NAME VARCHAR(48) NOT NULL,
    TXT_LAST_NAME VARCHAR(48) NOT NULL,
    PRIMARY KEY (ID_USER))
;


-- -----------------------------------------------------
-- Table Bookings
-- -----------------------------------------------------


CREATE TABLE Bookings (
    ID_BOOKING INT NOT NULL,
    VERSION INT NOT NULL,
    TST_CREATED_ON DATETIME NOT NULL,
    TST_UPDATED_ON VARCHAR(45) NOT NULL,
    TST_BOOKED_UNTIL DATETIME NOT NULL,
    TXT_BOOKING_STATUS VARCHAR(24) NOT NULL,
    FLOAT_BOOKING_PRICE FLOAT NOT NULL,
    INT_PRECISION INT NOT NULL,
    ID_CAR INT NOT NULL,
    ID_USER INT NOT NULL,
    PRIMARY KEY (ID_BOOKING),
    CONSTRAINT fk_Bookings_Cars
    FOREIGN KEY (ID_CAR)
    REFERENCES Cars (ID_CAR)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_Bookings_Users
    FOREIGN KEY (ID_USER)
    REFERENCES Users (ID_USER))
;

CREATE INDEX fk_Bookings_Cars_idx ON Bookings (ID_CAR);

CREATE INDEX fk_Bookings_Users_idx ON Bookings (ID_USER);

COMMIT;
