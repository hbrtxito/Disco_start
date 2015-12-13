CREATE DATABASE disc_store_db;



CREATE TABLE `disc_store_db`.`seller` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `disc_store_db`.`album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `artist_name` VARCHAR(45) NULL,
  `title` VARCHAR(45) NULL,
  `price` INT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `disc_store_db`.`album` 
CHANGE COLUMN `artist_name` `artist_name` VARCHAR(45) NULL DEFAULT 'No artist name' ,
CHANGE COLUMN `title` `title` VARCHAR(45) NULL DEFAULT 'No title' ,
CHANGE COLUMN `price` `price` INT(11) NULL DEFAULT 0 ,
ADD COLUMN `seller_id` INT NOT NULL AFTER `price`,
ADD INDEX `fk_seller_id_idx` (`seller_id` ASC);
ALTER TABLE `disc_store_db`.`album` 
ADD CONSTRAINT `fk_seller_id`
  FOREIGN KEY (`seller_id`)
  REFERENCES `disc_store_db`.`seller` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;


ALTER TABLE `disc_store_db`.`album` 
DROP FOREIGN KEY `fk_seller_id`;
ALTER TABLE `disc_store_db`.`album` 
ADD CONSTRAINT `fk_seller_id`
  FOREIGN KEY (`seller_id`)
  REFERENCES `disc_store_db`.`seller` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `disc_store_db`.`sales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` FLOAT NULL DEFAULT 1,
  `date_sale` DATETIME NULL,
  `album_id` INT NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `disc_store_db`.`sales` 
ADD INDEX `fk_album_id_idx` (`album_id` ASC);
ALTER TABLE `disc_store_db`.`sales` 
ADD CONSTRAINT `fk_album_id`
  FOREIGN KEY (`album_id`)
  REFERENCES `disc_store_db`.`album` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `disc_store_db`.`album` 
ADD COLUMN `en_sotano` INT NULL DEFAULT 0 AFTER `seller_id`;

ALTER TABLE `disc_store_db`.`album` 
ADD COLUMN `sold_on` INT NULL DEFAULT 0 AFTER `en_sotano`;

ALTER TABLE `disc_store_db`.`album`
ADD COLUMN `created_on` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `sold_on`;





