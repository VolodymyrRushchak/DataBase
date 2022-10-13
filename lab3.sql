CREATE DATABASE IF NOT EXISTS `Rushchak`;
USE `Rushchak` ;

DROP TABLE IF EXISTS `transaction` ;
DROP TABLE IF EXISTS `card` ;
DROP TABLE IF EXISTS `account` ;
DROP TABLE IF EXISTS `client` ;
DROP TABLE IF EXISTS `home_address` ;
DROP TABLE IF EXISTS `credentials` ;
DROP TABLE IF EXISTS `card_type` ;
DROP TABLE IF EXISTS `card_info` ;
DROP TABLE IF EXISTS `card_credentials` ;

CREATE TABLE `home_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `street_name` VARCHAR(45) NOT NULL,
  `house_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `home_address_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `surname_UNIQUE` (`surname`),
  INDEX `fk_client_home_address1_idx` (`home_address_id`),
  CONSTRAINT `fk_client_home_address1`
    FOREIGN KEY (`home_address_id`)
    REFERENCES `home_address` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE `credentials` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `account_name` VARCHAR(45) NOT NULL,
  `date_created` DATE NOT NULL,
  `credentials_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_client1_idx` (`client_id`),
  INDEX `fk_account_credentials1_idx` (`credentials_id`),
  CONSTRAINT `fk_account_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `Rushchak`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_credentials1`
    FOREIGN KEY (`credentials_id`)
    REFERENCES `Rushchak`.`credentials` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `card_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `interest_rate` FLOAT NULL,
  `minimum_deposit` FLOAT NULL,
  `other_details` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `card_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `balance` FLOAT NOT NULL,
  `card_name` VARCHAR(45) NULL,
  `date_opened` DATE NULL,
  `date_expires` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `card_name_UNIQUE` (`card_name`))
ENGINE = InnoDB;

CREATE TABLE `card_credentials` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `card_number` VARCHAR(16) NOT NULL,
  `cvv` VARCHAR(3) NOT NULL,
  `pin` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account_id` INT NOT NULL,
  `card_type_id` INT NOT NULL,
  `card_info_id` INT NOT NULL,
  `card_credentials_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_card_account1_idx` (`account_id`),
  INDEX `fk_card_account_type1_idx` (`card_type_id`),
  INDEX `fk_card_card_info1_idx` (`card_info_id`),
  INDEX `fk_card_card_credentials1_idx` (`card_credentials_id`),
  CONSTRAINT `fk_card_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `Rushchak`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_card_account_type1`
    FOREIGN KEY (`card_type_id`)
    REFERENCES `Rushchak`.`card_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_card_card_info1`
    FOREIGN KEY (`card_info_id`)
    REFERENCES `Rushchak`.`card_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_card_card_credentials1`
    FOREIGN KEY (`card_credentials_id`)
    REFERENCES `Rushchak`.`card_credentials` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `amount` FLOAT NOT NULL,
  `purpose` VARCHAR(150) NULL,
  `senders_card_id` INT NOT NULL,
  `receivers_card_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transaction_card1_idx` (`senders_card_id`),
  INDEX `fk_transaction_card2_idx` (`receivers_card_id`),
  CONSTRAINT `fk_transaction_card1`
    FOREIGN KEY (`senders_card_id`)
    REFERENCES `Rushchak`.`card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_card2`
    FOREIGN KEY (`receivers_card_id`)
    REFERENCES `Rushchak`.`card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `home_address` (`id`,`country`,`state`,`city`,`street_name`,`house_number`) VALUES 
(9,'Ukraine','Uman','Uman','Heroiv Upa','33'),
(10,'Great Britan','London','London','Bandery','121'),
(11,'Ukraine','Zytomyr','Zytomyr','Privet Drive','10'),
(12,'Ukraine','Ternopil','Ternopil','Halytska','54'),
(13,'USA','New-York','New-York','Somewhere ','12'),
(14,'Ukraine','Odesa','Odesa','Sichovyh Strilciv','11'),
(15,'Poland','Zryvka','Zryvka','Rainbow Shores Rd','76'),
(16,'Czech Republic','Tupka','Tupka','Home Address','22'),
(17,'Austria','Zunde','Zunde','Blabla Street','12'),
(18,'Canada','Toronto','Toronto','Bandery','31');

INSERT INTO `client` (`id`,`name`,`surname`,`phone_number`,`email`,`home_address_id`) VALUES 
(1,'Volodymyr','Rushchak','0964711358','rushvolod@gmai.com',9),
(2,'Sasha','Antonov','0962164142','sashant@gmail.com',10),
(3,'Harry','Potter','6054756961','harrypotter@gmail.com',11),
(4,'Alex','Dutchak','0664598786','alex@gmail.com',12),
(5,'Andriy','Shevchuk','0651324568','shevandr@gmail.com',13),
(6,'Anton','Hund','0666666116','ilovemarchmelo@ukr.net',14),
(7,'Grant','Sanderson','3155094331','grant@gmail.com',15),
(8,'Name','Surname','1010110111','namesurname@ukr.net',16),
(9,'Morty','Usyk','0965822469','morty@gmail.com',17),
(10,'Jack','Sally','0964711358','jackjack@gmail.com',18),
(11,'Test','Tester','0671247644','test@ukr.net',NULL);

INSERT INTO `credentials` (`id`,`login`,`password`) VALUES 
(10,'vova','13125'),
(11,'kola','164312'),
(12,'dady','1236547'),
(13,'mamy','13234'),
(14,'blad','12368765'),
(15,'petro','8543'),
(16,'anton','43576'),
(17,'harry','12545'),
(18,'james','23756'),
(19,'ht5h4','6852');

INSERT INTO `account` (`id`,`client_id`,`account_name`,`date_created`,`credentials_id`) VALUES 
(1,1,'Zhora','2007-08-20',10),
(2,2,'Vova','2009-10-01',11),
(3,3,'Kolia','2001-04-15',12),
(4,4,'Andrew','2021-08-11',13),
(5,5,'James Bond','1999-12-30',14),
(6,6,'Vasyl','2003-12-30',15),
(7,7,'Harry','2004-01-21',16),
(8,8,'Dady','2005-02-22',17),
(9,9,'Mamy','2006-03-23',18),
(10,10,'Baby','2007-04-24',19);

INSERT INTO `card_type` (`id`,`interest_rate`,`minimum_deposit`,`other_details`) VALUES 
(9,0,0,NULL),
(10,2,100,'payment only'),
(11,0,0,'low liquidity'),
(12,10,5000,'special clients only'),
(13,15,15000,'cheks are forbidden'),
(14,21,100000,'salary not allowed'),
(15,1,0,'cheks are forbidden, cash withdrawal are limited'),
(16,3,200,'cheks are forbidden, cash withdrawal are limited, low liquidity'),
(17,5,500,'no limits'),
(18,0,0,'credits are available');

INSERT INTO `card_info` (`id`,`balance`,`card_name`,`date_opened`,`date_expires`) VALUES 
(1,1100,'mycard','2007-08-10','2021-08-10'),
(2,5167,'mycard2','2009-09-01','2023-09-01'),
(3,13475900,'plastic','2001-02-15','2023-02-15'),
(4,1251,'babycard','2021-02-11','2023-02-11'),
(5,98765,'antoncard','1999-10-30','2023-10-30'),
(6,45358,'cardcard','2003-11-30','2023-11-30'),
(7,353,'aicard','2004-01-11','2022-01-11'),
(8,57432,'machinelearningcard','2005-01-22','2023-01-22'),
(9,12617,'petrocard','2006-01-23','2023-01-23'),
(10,26426500,'goldcard','2007-03-24','2023-03-24');

INSERT INTO `card_credentials` (`id`,`card_number`,`cvv`,`pin`) VALUES 
(1,'3184984113561412','552','1256'),
(2,'5184184124358798','266','7743'),
(3,'6012678296371235','122','4779'),
(4,'9867825472437211','315','2235'),
(5,'1731785000002421','764','8876'),
(6,'6923926468200085','377','2222'),
(7,'7431836591650076','126','1111'),
(8,'2419575491245611','216','3333'),
(9,'6719273481264627','976','7765'),
(10,'8714617536213563','544','6789');

INSERT INTO `card` (`id`,`account_id`,`card_type_id`,`card_info_id`,`card_credentials_id`) VALUES 
(1,1,9,1,1),
(2,2,10,2,2),
(3,3,11,3,3),
(4,4,12,4,4),
(5,5,13,5,5),
(6,6,14,6,6),
(7,7,15,7,7),
(8,8,16,8,8),
(9,9,17,9,9),
(10,10,18,10,10);

INSERT INTO `transaction` (`id`,`date`,`amount`,`purpose`,`senders_card_id`,`receivers_card_id`) VALUES 
(1,'2022-08-08',12000,NULL,5,1),
(2,'2022-07-07',3000,'Just for fun',1,2),
(3,'2021-06-12',5511,'Salary',9,3),
(4,'2020-11-13',666,'Intimidating',8,4),
(5,'1999-09-15',1200,'Furniture purchase',3,5),
(6,'2019-10-10',7879120,'Telsa purchase',2,6),
(7,'2018-03-16',13513,'Idk why',7,7),
(8,'2017-12-30',3000,'Birthday gift',6,8),
(9,'2016-11-29',5012,'Wedding gift',4,9),
(10,'2015-10-28',112233,'Gift of fate',3,10);
