-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema expensereimbersement
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema expensereimbersement
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `expensereimbersement` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `expensereimbersement` ;

-- -----------------------------------------------------
-- Table `expensereimbersement`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `expensereimbersement`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `expensereimbersement`.`expenseticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `expensereimbersement`.`expenseticket` (
  `ticket_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `department` VARCHAR(45) NULL DEFAULT NULL,
  `notes` VARCHAR(500) NULL DEFAULT NULL,
  `total_cost` DOUBLE NULL DEFAULT '0',
  `status_id` INT NOT NULL DEFAULT '1',
  PRIMARY KEY (`ticket_id`),
  INDEX `status_id_idx` (`status_id` ASC),
  CONSTRAINT `status_id`
    FOREIGN KEY (`status_id`)
    REFERENCES `expensereimbersement`.`status` (`status_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 391
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `expensereimbersement`.`expense`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `expensereimbersement`.`expense` (
  `expense_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `cost` DOUBLE NOT NULL,
  `ticket_id` INT NOT NULL,
  PRIMARY KEY (`expense_id`),
  INDEX `ticket_id_idx` (`ticket_id` ASC),
  CONSTRAINT `FK_ticket_id`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `expensereimbersement`.`expenseticket` (`ticket_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
