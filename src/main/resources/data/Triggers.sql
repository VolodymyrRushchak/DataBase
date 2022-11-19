USE rushchak;

DROP TRIGGER IF EXISTS account_delete_constrain_trig;
DROP TRIGGER IF EXISTS account_update_constrain_trig;
DROP TRIGGER IF EXISTS debt_insert_constrain_trig;
DROP TRIGGER IF EXISTS debt_update_constrain_trig;
DROP TRIGGER IF EXISTS card_num_min_length_trig;
DROP TRIGGER IF EXISTS transaction_update_trig;
DROP TRIGGER IF EXISTS client_delete_trig;

DELIMITER //
CREATE TRIGGER account_delete_constrain_trig
    BEFORE DELETE
    ON account FOR EACH ROW
BEGIN
    DECLARE num int;
    SELECT COUNT(*) INTO num FROM debt WHERE account_id = old.id;
    IF num != 0 THEN SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot delete this account because it has debts';
    END IF;
END //

CREATE TRIGGER account_update_constrain_trig
    BEFORE UPDATE
    ON account FOR EACH ROW
BEGIN
    DECLARE num int;
    SELECT COUNT(*) INTO num FROM debt WHERE account_id = old.id;
    IF num != 0 THEN SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot update this account because it has debts';
    END IF;
END //

CREATE TRIGGER debt_insert_constrain_trig
    BEFORE INSERT
    ON debt FOR EACH ROW
BEGIN
    DECLARE num int;
    SELECT COUNT(*) INTO num FROM account WHERE id = new.account_id;
    IF num = 0 THEN SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot insert debt referencing not existing account';
    END IF;
END //

CREATE TRIGGER debt_update_constrain_trig
    BEFORE UPDATE
    ON debt FOR EACH ROW
BEGIN
    DECLARE num int;
    SELECT COUNT(*) INTO num FROM account WHERE id = new.account_id;
    IF num = 0 THEN SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot set account_id referencing not existing account';
    END IF;
END //

CREATE TRIGGER card_num_min_length_trig
    BEFORE INSERT
    ON card_credentials FOR EACH ROW
BEGIN
    IF LENGTH(new.card_number) != 16 THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Card number must be 16-digit';
    END IF;
END //

CREATE TRIGGER transaction_update_trig
    BEFORE UPDATE
    ON transaction FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Transaction data cannot be modified';
END //

CREATE TRIGGER client_delete_trig
    BEFORE DELETE
    ON client FOR EACH ROW
BEGIN
    INSERT INTO client_logs(action, date, surname)
        VALUES ('delete', CURDATE(), old.surname);
END //

DELIMITER ;