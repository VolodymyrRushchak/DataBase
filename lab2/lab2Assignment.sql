# 1
SELECT * FROM labor_sql.income 
WHERE income.point = 1 
ORDER BY income.inc;


# 2 
SELECT SUBSTRING_INDEX(name, ' ', -1) AS Surname 
FROM labor_sql.passenger
WHERE SUBSTRING_INDEX(name, ' ', -1) NOT LIKE 'J%';


# 3 
SELECT ship, battle, date
FROM labor_sql.outcomes
INNER JOIN labor_sql.battles ON outcomes.battle = battles.name
WHERE result = 'OK';


# 4
SELECT model, price
FROM labor_sql.laptop
WHERE price > ALL 
	(
		SELECT price FROM labor_sql.pc 
	);
  
  
# 5
SELECT DISTINCT maker FROM labor_sql.product TEMP
WHERE EXISTS 
	(
		SELECT code FROM labor_sql.laptop
        WHERE speed >= 750 AND laptop.model IN (SELECT model FROM labor_sql.product WHERE maker = TEMP.maker)
	) 
    AND EXISTS
    (
		SELECT code FROM labor_sql.pc
        WHERE speed >= 750 AND pc.model IN (SELECT model FROM labor_sql.product WHERE maker = TEMP.maker)
    );
    
    
# 6
SELECT CONCAT('Код: ', code) AS Code, CONCAT('Модель: ', model) AS Model, 
	   CONCAT('Колір: ', color) AS Color, CONCAT('Тип: ', type) AS Type, CONCAT('Ціна: ', price) AS Price
FROM labor_sql.printer;


#7
SELECT date, COUNT(CONCAT(trip_no, date, ID_psg)) AS Quantity FROM labor_sql.pass_in_trip
GROUP BY date;


# 8
SELECT maker, COUNT(pc.code) AS PCS, COUNT(laptop.code) AS LAPTOPS, COUNT(printer.code) AS PRINTERS
FROM labor_sql.product
LEFT JOIN labor_sql.pc ON pc.model = product.model
LEFT JOIN labor_sql.laptop ON laptop.model = product.model
LEFT JOIN labor_sql.printer ON printer.model = product.model
GROUP BY maker;


# 9
SELECT outcome.point, outcome.date, 
CASE 
	WHEN SUM(outcome.out) > IFNULL(outcome_o.out, 0) THEN 'more than once a day'
    WHEN SUM(outcome.out) < outcome_o.out THEN 'once a day'
    ELSE 'both'
END AS WhoIsBigger
FROM labor_sql.outcome
LEFT JOIN labor_sql.outcome_o ON outcome.point = outcome_o.point AND outcome.date = outcome_o.date 
GROUP BY point, date
UNION
SELECT outcome_o.point, outcome_o.date, 
CASE 
	WHEN IFNULL(SUM(outcome.out), 0) < outcome_o.out THEN 'once a day'
    WHEN SUM(outcome.out) > outcome_o.out THEN 'more than once a day'
    ELSE 'both'
END AS WhoIsBigger
FROM labor_sql.outcome
RIGHT JOIN labor_sql.outcome_o ON outcome.point = outcome_o.point AND outcome.date = outcome_o.date 
GROUP BY point, date;


# 10
SELECT name, launched FROM labor_sql.ships
WHERE launched < 1942;



    
