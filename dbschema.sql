SET FOREIGN_KEY_CHECKS=0
; 
/* Drop Tables */

DROP TABLE IF EXISTS `Account` CASCADE
;

DROP TABLE IF EXISTS `Category` CASCADE
;

DROP TABLE IF EXISTS `Transaction` CASCADE
;

DROP TABLE IF EXISTS `User` CASCADE
;

/* Create Tables */

CREATE TABLE `Account`
(
	`name` VARCHAR(50) NOT NULL,
	`description` VARCHAR(50) NOT NULL,
	`entryBalance` DECIMAL(10,2) NOT NULL,
	`currentBalance` DECIMAL(10,2) NOT NULL,
	`currency` VARCHAR(3) NOT NULL,
	`createdAt` DATETIME NOT NULL,
	`id` INT NOT NULL,
	`updatedAt` VARCHAR(50) NOT NULL,
	`deleted` BOOL NOT NULL,
	CONSTRAINT `PK_Account` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `Category`
(
	`name` VARCHAR(50) NOT NULL,
	`description` VARCHAR(50) NOT NULL,
	`color` VARCHAR(50) NOT NULL,
	`deleted` VARCHAR(50) NOT NULL,
	`createdAt` VARCHAR(50) NOT NULL,
	`deletedAt` VARCHAR(50) NOT NULL,
	`id` INT NOT NULL,
	`transaction_id` INT NULL,
	CONSTRAINT `PK_Category` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `Transaction`
(
	`id` INT NOT NULL,
	`createdOn` DATE NOT NULL,
	`updatedOn` DATE NOT NULL,
	`deleted` BOOL NOT NULL,
	`description` VARCHAR(50) NOT NULL,
	`amount` DECIMAL(10,2) NOT NULL,
	`user_id` INT NOT NULL,
	`cost` BOOL NOT NULL,
	`account_id` INT NULL,
	`category_id` INT NULL,
	CONSTRAINT `PK_Transaction` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `User`
(
	`id` INT NOT NULL,
	`name` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	`deleted` BOOL NOT NULL,
	`createdAt` DATETIME NOT NULL,
	`UpdatedAt` DATETIME NOT NULL,
	CONSTRAINT `PK_User` PRIMARY KEY (`id` ASC)
)

;

/* Create Foreign Key Constraints */

ALTER TABLE `Transaction` 
 ADD CONSTRAINT `FK_Transaction_Account`
	FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Transaction` 
 ADD CONSTRAINT `FK_Transaction_Category`
	FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Transaction` 
 ADD CONSTRAINT `FK_Transaction_User`
	FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

SET FOREIGN_KEY_CHECKS=1
; 
