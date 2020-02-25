-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema adabank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema adabank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `adabank` DEFAULT CHARACTER SET utf8 ;
USE `adabank` ;

-- -----------------------------------------------------
-- Table `adabank`.`account_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`account_type` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `code_control` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adabank`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`country` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `iban` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adabank`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`bank` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `code` VARCHAR(100) NOT NULL,
  `Country_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Bank_Country1_idx` (`Country_id` ASC) VISIBLE,
  CONSTRAINT `fk_Bank_Country1`
    FOREIGN KEY (`Country_id`)
    REFERENCES `adabank`.`country` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adabank`.`branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`branch` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `code` VARCHAR(10) NOT NULL,
  `Bank_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Branch_Bank1_idx` (`Bank_id` ASC) VISIBLE,
  CONSTRAINT `fk_Branch_Bank1`
    FOREIGN KEY (`Bank_id`)
    REFERENCES `adabank`.`bank` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adabank`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`client` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `type_doc` VARCHAR(45) NOT NULL,
  `doc` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adabank`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` INT(11) NOT NULL,
  `currency` VARCHAR(45) NOT NULL,
  `balance` DOUBLE NULL DEFAULT NULL,
  `controlNumber` VARCHAR(45) NULL,
  `Branch_id` INT(11) NOT NULL,
  `Client_id` INT(11) NOT NULL,
  `Account_Type_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Account_Branch1_idx` (`Branch_id` ASC) VISIBLE,
  INDEX `fk_Account_Client1_idx` (`Client_id` ASC) VISIBLE,
  INDEX `fk_Account_Account_Type1_idx` (`Account_Type_id` ASC) VISIBLE,
  CONSTRAINT `fk_Account_Account_Type1`
    FOREIGN KEY (`Account_Type_id`)
    REFERENCES `adabank`.`account_type` (`id`),
  CONSTRAINT `fk_Account_Branch1`
    FOREIGN KEY (`Branch_id`)
    REFERENCES `adabank`.`branch` (`id`),
  CONSTRAINT `fk_Account_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `adabank`.`client` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adabank`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `mail` VARCHAR(100) NOT NULL,
  `telephone` INT(11) NOT NULL,
  `Client_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Contact_Client1_idx` (`Client_id` ASC) VISIBLE,
  CONSTRAINT `fk_Contact_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `adabank`.`client` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adabank`.`movement_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`movement_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adabank`.`movement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adabank`.`movement` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `amount` DOUBLE NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `Movement_Type_id` INT(11) NOT NULL,
  `Account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Movement_Movement_Type1_idx` (`Movement_Type_id` ASC) VISIBLE,
  INDEX `fk_Movement_Account1_idx` (`Account_id` ASC) VISIBLE,
  CONSTRAINT `fk_Movement_Account1`
    FOREIGN KEY (`Account_id`)
    REFERENCES `adabank`.`account` (`id`),
  CONSTRAINT `fk_Movement_Movement_Type1`
    FOREIGN KEY (`Movement_Type_id`)
    REFERENCES `adabank`.`movement_type` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
