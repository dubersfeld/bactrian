DROP DATABASE IF EXISTS bactrian_customers;

CREATE DATABASE bactrian_customers DEFAULT CHARACTER SET 'utf8'
  DEFAULT COLLATE 'utf8_unicode_ci';

USE bactrian_customers;


CREATE TABLE IF NOT EXISTS `customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(32),
  `last_name` VARCHAR(32),
  `city` VARCHAR(32),
  PRIMARY KEY (`id`),
  CONSTRAINT customer_unique UNIQUE (first_name, last_name)
) ENGINE=InnoDB;


INSERT INTO customer VALUES (
  '100', 'George', 'Clooney', 'New York'
);

INSERT INTO customer VALUES (
  '101', 'Paul', 'Enclume', 'Paris'
);

INSERT INTO customer VALUES (
  '102', 'Alice', 'Carroll', 'London'
);


