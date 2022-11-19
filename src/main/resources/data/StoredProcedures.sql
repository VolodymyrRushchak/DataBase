USE rushchak;

DROP FUNCTION IF EXISTS get_min_balance;
DROP PROCEDURE IF EXISTS get_min_balance;
DROP PROCEDURE IF EXISTS add_card_cred;
DROP PROCEDURE IF EXISTS add_transaction;
DROP PROCEDURE IF EXISTS add_pack_of_clients;
DROP PROCEDURE IF EXISTS use_cursor;

DELIMITER //

CREATE FUNCTION get_min_balance() RETURNS int
BEGIN
    RETURN (SELECT MIN(balance) FROM card_info);
END //

CREATE PROCEDURE get_min_balance()
BEGIN
    SELECT get_min_balance() AS MinBalance;
END //

CREATE PROCEDURE add_card_cred(IN new_card_num varchar(16), IN new_cvv varchar(3), IN new_pin varchar(4))
BEGIN
    INSERT INTO card_credentials (card_number, cvv, pin)
    VALUES (new_card_num, new_cvv, new_pin);
END //

CREATE PROCEDURE add_transaction(IN sender_card_name varchar(45), IN receiver_card_name varchar(45), IN new_amount float)
BEGIN
    DECLARE sender_info_id int;
    DECLARE receiver_info_id int;
    DECLARE sender_id int;
    DECLARE receiver_id int;
    SELECT id INTO sender_info_id FROM card_info WHERE card_name = sender_card_name;
    SELECT id INTO receiver_info_id FROM card_info WHERE card_name = receiver_card_name;
    SELECT id INTO sender_id FROM card WHERE card_info_id = sender_info_id;
    SELECT id INTO receiver_id FROM card WHERE card_info_id = receiver_info_id;
    INSERT INTO transaction (date, amount, senders_card_id, receivers_card_id)
        VALUES (NOW(), new_amount, sender_id, receiver_id);
END //

CREATE PROCEDURE add_pack_of_clients(IN new_name varchar(45), IN new_surname varchar(45))
BEGIN
   DECLARE num int;
   SET num = 1;
   WHILE num < 11 DO
        INSERT INTO client(name, surname)
            VALUES (CONCAT(new_name, num), CONCAT(new_surname, num));
        SET num = num + 1;
   END WHILE;
END //

CREATE PROCEDURE use_cursor()
BEGIN
    DECLARE done int DEFAULT false;
    DECLARE surname_t varchar(45);
    DECLARE client_cursor CURSOR
        FOR SELECT surname FROM client;
    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    OPEN client_cursor;

    myloop: LOOP
        FETCH client_cursor INTO surname_t;
        IF done = true THEN LEAVE myloop;
        END IF;
        SET @query_t = CONCAT('CREATE TABLE ', surname_t, '_', CURDATE()+0, '(',
            'id INT NOT NULL AUTO_INCREMENT,',
            'property VARCHAR(45) NOT NULL,',
            'price FLOAT NOT NULL,',
            'PRIMARY KEY (id)', ');');
        PREPARE myquery FROM @query_t;
        EXECUTE myquery;
        DEALLOCATE PREPARE myquery;
    END LOOP;

    CLOSE client_cursor;
END //

DELIMITER ;