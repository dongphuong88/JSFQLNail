use qlnail;
CREATE TABLE `qlnail`.`calendar_transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `transaction_id` INT NULL,
  `start_time` TIME(0) NULL,
  `end_time` TIME(0) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `qlnail`.`calendar_services` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `staff_id` INT NULL,
  `service_id` INT NULL,
  `customer_id` INT NULL,
  `start_time` TIME(0) NULL,
  `end_time` TIME(0) NULL,
  `note` VARCHAR(200) NULL,
  PRIMARY KEY (`id`));