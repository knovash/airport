-- MySQL Script generated by MySQL Workbench
-- Sat 17 Sep 2022 09:56:17 PM +03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema airport
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `airport` ;

-- -----------------------------------------------------
-- Schema airport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `airport` ;
USE `airport` ;

-- -----------------------------------------------------
-- Table `airport`.`airports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`airports` ;

CREATE TABLE IF NOT EXISTS `airport`.`airports` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `airport_name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`aircarriers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`aircarriers` ;

CREATE TABLE IF NOT EXISTS `airport`.`aircarriers` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `aircarrier_name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`pilots_licenses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`pilots_licenses` ;

CREATE TABLE IF NOT EXISTS `airport`.`pilots_licenses` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number` BIGINT NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `license_number_UNIQUE` (`number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`pilots`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`pilots` ;

CREATE TABLE IF NOT EXISTS `airport`.`pilots` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pilot_license_id` BIGINT UNSIGNED NOT NULL,
  `aircarrier_id` BIGINT UNSIGNED NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pilots_air_carrier1_idx` (`aircarrier_id` ASC) VISIBLE,
  INDEX `fk_pilots_pilots_licenses1_idx` (`pilot_license_id` ASC) VISIBLE,
  UNIQUE INDEX `pilot_license_id_UNIQUE` (`pilot_license_id` ASC) VISIBLE,
  CONSTRAINT `fk_pilots_air_carrier1`
    FOREIGN KEY (`aircarrier_id`)
    REFERENCES `airport`.`aircarriers` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pilots_pilots_licenses1`
    FOREIGN KEY (`pilot_license_id`)
    REFERENCES `airport`.`pilots_licenses` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`passports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`passports` ;

CREATE TABLE IF NOT EXISTS `airport`.`passports` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `passport_number_UNIQUE` (`number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`passengers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`passengers` ;

CREATE TABLE IF NOT EXISTS `airport`.`passengers` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `passport_id` BIGINT UNSIGNED NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_passengers_passports_idx` (`passport_id` ASC) VISIBLE,
  UNIQUE INDEX `passport_id_UNIQUE` (`passport_id` ASC) VISIBLE,
  CONSTRAINT `fk_passengers_passports`
    FOREIGN KEY (`passport_id`)
    REFERENCES `airport`.`passports` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`directions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`directions` ;

CREATE TABLE IF NOT EXISTS `airport`.`directions` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(50) NOT NULL,
  `distance` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `country_UNIQUE` (`country` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`aircrafts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`aircrafts` ;

CREATE TABLE IF NOT EXISTS `airport`.`aircrafts` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `aircarrier_id` BIGINT UNSIGNED NOT NULL,
  `number` BIGINT UNSIGNED NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `seats` BIGINT UNSIGNED NOT NULL,
  `service_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `aircraft_number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_aircrafts_air_carrier1_idx` (`aircarrier_id` ASC) VISIBLE,
  CONSTRAINT `fk_aircraft_air_carrier1`
    FOREIGN KEY (`aircarrier_id`)
    REFERENCES `airport`.`aircarriers` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`airstrips`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`airstrips` ;

CREATE TABLE IF NOT EXISTS `airport`.`airstrips` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `airport_id` BIGINT UNSIGNED NOT NULL,
  `number` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_airstrip_airports1_idx` (`airport_id` ASC) VISIBLE,
  CONSTRAINT `fk_airstrip_airports1`
    FOREIGN KEY (`airport_id`)
    REFERENCES `airport`.`airports` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`flights`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`flights` ;

CREATE TABLE IF NOT EXISTS `airport`.`flights` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `aircarrier_id` BIGINT UNSIGNED NOT NULL,
  `aircraft_id` BIGINT UNSIGNED NOT NULL,
  `airstrip_id` BIGINT UNSIGNED NOT NULL,
  `direction_id` BIGINT UNSIGNED NOT NULL,
  `number` BIGINT UNSIGNED NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `flight_number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_flights_directions1_idx` (`direction_id` ASC) VISIBLE,
  INDEX `fk_flights_aircrafts1_idx` (`aircraft_id` ASC) VISIBLE,
  INDEX `fk_flights_airstrip1_idx` (`airstrip_id` ASC) VISIBLE,
  INDEX `fk_flights_aircarriers1_idx` (`aircarrier_id` ASC) VISIBLE,
  CONSTRAINT `fk_flights_directions1`
    FOREIGN KEY (`direction_id`)
    REFERENCES `airport`.`directions` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flights_aircrafts1`
    FOREIGN KEY (`aircraft_id`)
    REFERENCES `airport`.`aircrafts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flights_airstrip1`
    FOREIGN KEY (`airstrip_id`)
    REFERENCES `airport`.`airstrips` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flights_aircarriers1`
    FOREIGN KEY (`aircarrier_id`)
    REFERENCES `airport`.`aircarriers` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`gates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`gates` ;

CREATE TABLE IF NOT EXISTS `airport`.`gates` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `airport_id` BIGINT UNSIGNED NOT NULL,
  `number` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_gates_airports1_idx` (`airport_id` ASC) VISIBLE,
  CONSTRAINT `fk_gate_airports1`
    FOREIGN KEY (`airport_id`)
    REFERENCES `airport`.`airports` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`tickets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`tickets` ;

CREATE TABLE IF NOT EXISTS `airport`.`tickets` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `flight_id` BIGINT UNSIGNED NOT NULL,
  `passenger_id` BIGINT UNSIGNED NOT NULL,
  `gate_id` BIGINT UNSIGNED NOT NULL,
  `price` BIGINT UNSIGNED NOT NULL,
  `number` BIGINT UNSIGNED NOT NULL,
  `seat` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tickets_gates1_idx` (`gate_id` ASC) VISIBLE,
  INDEX `fk_tickets_passengers1_idx` (`passenger_id` ASC) VISIBLE,
  INDEX `fk_tickets_flights1_idx` (`flight_id` ASC) VISIBLE,
  UNIQUE INDEX `ticket_number_UNIQUE` (`number` ASC) VISIBLE,
  CONSTRAINT `fk_tickets_gates1`
    FOREIGN KEY (`gate_id`)
    REFERENCES `airport`.`gates` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_passengers1`
    FOREIGN KEY (`passenger_id`)
    REFERENCES `airport`.`passengers` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_flights1`
    FOREIGN KEY (`flight_id`)
    REFERENCES `airport`.`flights` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`airport_aircarriers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`airport_aircarriers` ;

CREATE TABLE IF NOT EXISTS `airport`.`airport_aircarriers` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `airport_id` BIGINT UNSIGNED NOT NULL,
  `aircarrier_id` BIGINT UNSIGNED NOT NULL,
  INDEX `fk_aircarriers_has_airports_airports1_idx` (`airport_id` ASC) VISIBLE,
  INDEX `fk_aircarriers_has_airports_aircarriers1_idx` (`aircarrier_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_aircarriers_has_airports_aircarriers1`
    FOREIGN KEY (`aircarrier_id`)
    REFERENCES `airport`.`aircarriers` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aircarriers_has_airports_airports1`
    FOREIGN KEY (`airport_id`)
    REFERENCES `airport`.`airports` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`pilot_flights`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airport`.`pilot_flights` ;

CREATE TABLE IF NOT EXISTS `airport`.`pilot_flights` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pilot_id` BIGINT UNSIGNED NOT NULL,
  `flight_id` BIGINT UNSIGNED NOT NULL,
  INDEX `fk_flights_has_pilots_pilots1_idx` (`pilot_id` ASC) VISIBLE,
  INDEX `fk_flights_has_pilots_flights1_idx` (`flight_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_flights_has_pilots_flights1`
    FOREIGN KEY (`flight_id`)
    REFERENCES `airport`.`flights` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flights_has_pilots_pilots1`
    FOREIGN KEY (`pilot_id`)
    REFERENCES `airport`.`pilots` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
