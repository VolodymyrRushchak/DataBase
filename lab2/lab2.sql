/*# 1
SELECT AVG(balance) AS average_balance FROM rushchak.card_info; 

# 2
SELECT * FROM rushchak.client 
WHERE home_address_id IN (SELECT id FROM rushchak.home_address WHERE country = 'Ukraine');

# 3
SELECT * FROM rushchak.client 
WHERE email LIKE '%ukr.net';

# 4
SELECT * FROM rushchak.card_info 
WHERE date_expires < NOW();

# 5
SELECT CONCAT(client.name, ' ', client.surname) AS Client, card_info.balance
FROM rushchak.client
INNER JOIN rushchak.account ON client.id = account.client_id
INNER JOIN rushchak.card ON account.id = card.account_id
INNER JOIN rushchak.card_info ON card.card_info_id = card_info.id;


# 6
SELECT * FROM rushchak.account
INNER JOIN rushchak.client ON account.client_id = client.id
ORDER BY account.account_name;

#7
SELECT CONCAT(sender.name, ' ', sender.surname) AS Sender, transaction.amount, CONCAT(receiver.name, ' ', receiver.surname) AS Receiver
FROM rushchak.client sender, rushchak.client receiver,
	 rushchak.account sender_acc, rushchak.account receiver_acc, 
     rushchak.card sender_card, rushchak.card receiver_card, 
     rushchak.transaction
WHERE sender_card.id = transaction.senders_card_id AND receiver_card.id = transaction.receivers_card_id
  AND sender_acc.id = sender_card.account_id AND receiver_acc.id = receiver_card.account_id
  AND sender.id = sender_acc.client_id AND receiver.id = receiver_acc.client_id
  AND transaction.amount > 3000;
  
#8
SELECT COUNT(client.id) as NumberOfClients, home_address.country
FROM rushchak.client
LEFT JOIN rushchak.home_address ON client.home_address_id = home_address.id
GROUP BY home_address.country
ORDER BY COUNT(client.id) DESC;*/

#9


