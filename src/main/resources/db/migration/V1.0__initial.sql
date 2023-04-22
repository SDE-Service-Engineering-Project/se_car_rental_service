-- -----------------------------------------------------
-- Schema car_rental
-- -----------------------------------------------------
DROP TABLE IF EXISTS refresh_tokens;
DROP TABLE IF EXISTS users ;

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
