-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema AdaBank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AdaBank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AdaBank` DEFAULT CHARACTER SET utf8 ;
USE `AdaBank` ;

-- -----------------------------------------------------
-- Table `AdaBank`.`Country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Country` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `iban` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AdaBank`.`Bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Bank` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `code` VARCHAR(10) NOT NULL,
  `Country_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Bank_Country1_idx` (`Country_id` ASC) VISIBLE,
  CONSTRAINT `fk_Bank_Country1`
    FOREIGN KEY (`Country_id`)
    REFERENCES `AdaBank`.`Country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AdaBank`.`Branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Branch` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `code` VARCHAR(10) NOT NULL,
  `Bank_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Branch_Bank1_idx` (`Bank_id` ASC) VISIBLE,
  CONSTRAINT `fk_Branch_Bank1`
    FOREIGN KEY (`Bank_id`)
    REFERENCES `AdaBank`.`Bank` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AdaBank`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Client` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `type_doc` VARCHAR(45) NOT NULL,
  `doc` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AdaBank`.`Account_Type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Account_Type` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `code_control` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AdaBank`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NOT NULL,
  `currency` VARCHAR(45) NOT NULL,
  `balance` DOUBLE NOT NULL,
  `controlNumber` VARCHAR(20) NOT NULL,
  `Branch_id` INT(11) NOT NULL,
  `Client_id` INT(11) NOT NULL,
  `Account_Type_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Account_Branch1_idx` (`Branch_id` ASC) VISIBLE,
  INDEX `fk_Account_Client1_idx` (`Client_id` ASC) VISIBLE,
  INDEX `fk_Account_Account_Type1_idx` (`Account_Type_id` ASC) VISIBLE,
  CONSTRAINT `fk_Account_Branch1`
    FOREIGN KEY (`Branch_id`)
    REFERENCES `AdaBank`.`Branch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `AdaBank`.`Client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_Account_Type1`
    FOREIGN KEY (`Account_Type_id`)
    REFERENCES `AdaBank`.`Account_Type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AdaBank`.`Contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `mail` VARCHAR(100) NOT NULL,
  `telephone` INT(11) NOT NULL,
  `Client_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Contact_Client1_idx` (`Client_id` ASC) VISIBLE,
  CONSTRAINT `fk_Contact_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `AdaBank`.`Client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AdaBank`.`Movement_Type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Movement_Type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AdaBank`.`Movement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdaBank`.`Movement` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `amount` DOUBLE NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `Movement_Type_id` INT(11) NOT NULL,
  `Account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Movement_Movement_Type1_idx` (`Movement_Type_id` ASC) VISIBLE,
  INDEX `fk_Movement_Account1_idx` (`Account_id` ASC) VISIBLE,
  CONSTRAINT `fk_Movement_Movement_Type1`
    FOREIGN KEY (`Movement_Type_id`)
    REFERENCES `AdaBank`.`Movement_Type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_Account1`
    FOREIGN KEY (`Account_id`)
    REFERENCES `AdaBank`.`Account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
